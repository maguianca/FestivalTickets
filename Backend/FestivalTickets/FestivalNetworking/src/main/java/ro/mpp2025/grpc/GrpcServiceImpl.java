package ro.mpp2025.grpc;

import io.grpc.stub.StreamObserver;
import ro.mpp2025.Domain.Angajat;
import ro.mpp2025.Domain.Spectacol;
import ro.mpp2025.Service.BiletReservationException;
import ro.mpp2025.Service.IAppService;
import ro.mpp2025.Service.ServiceException;
import ro.mpp2025.grpc.DTOFactory;
import ro.mpp2025.grpc.*;

import java.util.HashMap;
import java.util.Map;

public class GrpcServiceImpl extends FestivalServiceGrpc.FestivalServiceImplBase {
    private final IAppService service;
    private final Map<String, StreamObserver<Festivalgrpc.Spectacol>> observers = new HashMap<>();

    public GrpcServiceImpl(IAppService service) {
        this.service = service;
    }

    @Override
    public void login(Festivalgrpc.LoginRequest request, StreamObserver<Festivalgrpc.LoginResponse> responseObserver) {
        String user = request.getAngajat().getUsername();
        String pass = request.getAngajat().getPassword();

        try {
            service.loginAngajat(user, pass, spectacol -> {
                // callback pentru observer
                StreamObserver<Festivalgrpc.Spectacol> stream = observers.get(user);
                if (stream != null) {
                    stream.onNext(DTOFactory.toGrpcSpectacol(spectacol));
                }
            });

            Festivalgrpc.LoginResponse response = Festivalgrpc.LoginResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Login reușit")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (ServiceException e) {
            Festivalgrpc.LoginResponse response = Festivalgrpc.LoginResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage(e.getMessage())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getSpectacole(Festivalgrpc.GetSpectacoleRequest request, StreamObserver<Festivalgrpc.GetSpectacoleResponse> responseObserver) {
        try {
            Iterable<Spectacol> spectacole = service.getAllSpectacole();
            Festivalgrpc.GetSpectacoleResponse.Builder builder = Festivalgrpc.GetSpectacoleResponse.newBuilder();
            for (Spectacol s : spectacole) {
                builder.addSpectacole(DTOFactory.toGrpcSpectacol(s));
            }
            responseObserver.onNext(builder.build());
            responseObserver.onCompleted();
        } catch (ServiceException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void filterSpectacole(Festivalgrpc.FilterSpectacoleRequest request, StreamObserver<Festivalgrpc.FilterSpectacoleResponse> responseObserver) {
        try {
            var day = request.getDay();
            var spectacole = service.filterSpectacole(java.time.LocalDateTime.parse(day));
            Festivalgrpc.FilterSpectacoleResponse.Builder builder = Festivalgrpc.FilterSpectacoleResponse.newBuilder();
            for (Spectacol s : spectacole) {
                builder.addSpectacole(DTOFactory.toGrpcSpectacol(s));
            }
            responseObserver.onNext(builder.build());
            responseObserver.onCompleted();
        } catch (ServiceException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void reserveBilet(Festivalgrpc.ReserveBiletRequest request, StreamObserver<Festivalgrpc.ReserveBiletResponse> responseObserver) {
        try {
            var bilet = request.getBilet();
            var spectacolDomain = DTOFactory.fromGrpcSpectacol(bilet.getSpectacol());

            service.reserveBilet(
                    spectacolDomain,
                    bilet.getCumparatorName(),
                    bilet.getSeats()
            );

            Festivalgrpc.ReserveBiletResponse response = Festivalgrpc.ReserveBiletResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Bilet rezervat")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();

            for (StreamObserver<Festivalgrpc.Spectacol> obs : observers.values()) {
                obs.onNext(DTOFactory.toGrpcSpectacol(spectacolDomain));
            }

        } catch (ServiceException | BiletReservationException e) {
            Festivalgrpc.ReserveBiletResponse response = Festivalgrpc.ReserveBiletResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage(e.getMessage())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }


    @Override
    public void subscribeUpdates(Festivalgrpc.Angajat request, StreamObserver<Festivalgrpc.Spectacol> responseObserver) {
        String user = request.getUsername();
        observers.put(user, responseObserver);
        //stream ul ramane deschis pt update
    }
}
