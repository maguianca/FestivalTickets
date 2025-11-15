package ro.mpp2025.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import ro.mpp2025.Domain.Angajat;
import ro.mpp2025.Domain.Spectacol;
import ro.mpp2025.Service.IAppService;
import ro.mpp2025.Service.ServiceException;
import ro.mpp2025.observer.Observer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GrpcServiceProxy implements IAppService {
    private FestivalServiceGrpc.FestivalServiceBlockingStub blockingStub;
    private FestivalServiceGrpc.FestivalServiceStub asyncStub;
    private ManagedChannel channel;
    private Observer client;

    public GrpcServiceProxy(String host, int port) {
        channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = FestivalServiceGrpc.newBlockingStub(channel);
        asyncStub = FestivalServiceGrpc.newStub(channel);
    }

    @Override
    public Angajat loginAngajat(String username, String password, Observer client) throws ServiceException {
        Festivalgrpc.LoginRequest request = Festivalgrpc.LoginRequest.newBuilder()
                .setAngajat(Festivalgrpc.Angajat.newBuilder()
                        .setUsername(username)
                        .setPassword(password)
                        .build())
                .build();

        Festivalgrpc.LoginResponse response = blockingStub.login(request);
        if (!response.getSuccess()) {
            throw new ServiceException(response.getMessage());
        }

        this.client = client;
        Festivalgrpc.Angajat grpcAngajat = Festivalgrpc.Angajat.newBuilder()
                .setUsername(username)
                .setPassword(password)
                .build();

        asyncStub.subscribeUpdates(grpcAngajat, new StreamObserver<Festivalgrpc.Spectacol>() {
            @Override
            public void onNext(Festivalgrpc.Spectacol grpcSpectacol) {
                Spectacol spectacol = DTOFactory.fromGrpcSpectacol(grpcSpectacol);
                System.out.println("Update primit: " + spectacol);
                try {
                    client.updatedSpectacol(spectacol);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Eroare la stream updates: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Stream de updates incheiat");
            }
        });

        return new Angajat(username, password, "");
    }

    @Override
    public void registerAngajat(String username, String password, String email) throws ServiceException {
        throw new UnsupportedOperationException("Nu este implementat in gRPC.");
    }

    @Override
    public Iterable<Spectacol> getAllSpectacole() throws ServiceException {
        Festivalgrpc.GetSpectacoleResponse response =
                blockingStub.getSpectacole(Festivalgrpc.GetSpectacoleRequest.newBuilder().build());

        List<Spectacol> result = new ArrayList<>();
        for (Festivalgrpc.Spectacol s : response.getSpectacoleList()) {
            result.add(DTOFactory.fromGrpcSpectacol(s));
        }
        return result;
    }

    @Override
    public Iterable<Spectacol> filterSpectacole(LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }

    @Override
    public Iterable<Spectacol> filterSpectacole(LocalDateTime day) throws ServiceException {
        Festivalgrpc.FilterSpectacoleRequest request = Festivalgrpc.FilterSpectacoleRequest.newBuilder()
                .setDay(day.toString())
                .build();

        Festivalgrpc.FilterSpectacoleResponse response = blockingStub.filterSpectacole(request);

        List<Spectacol> result = new ArrayList<>();
        for (Festivalgrpc.Spectacol s : response.getSpectacoleList()) {
            result.add(DTOFactory.fromGrpcSpectacol(s));
        }
        return result;
    }

    @Override
    public void reserveBilet(Spectacol spectacol, String cumparatorNume, int seats) throws ServiceException {
        Festivalgrpc.ReserveBiletRequest request = Festivalgrpc.ReserveBiletRequest.newBuilder()
                .setBilet(Festivalgrpc.Bilet.newBuilder()
                        .setSpectacol(DTOFactory.toGrpcSpectacol(spectacol))
                        .setCumparatorName(cumparatorNume)
                        .setSeats(seats)
                        .build())
                .build();

        Festivalgrpc.ReserveBiletResponse response = blockingStub.reserveBilet(request);
        if (!response.getSuccess()) {
            throw new ServiceException(response.getMessage());
        }
    }

    @Override
    public void logout(Angajat angajat) {
        if (channel != null && !channel.isShutdown()) {
            channel.shutdown();
        }
        this.client = null;
    }
}
