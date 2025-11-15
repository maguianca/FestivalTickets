package ro.mpp2025.protobuffprotocol;

import ro.mpp2025.Domain.Angajat;
import ro.mpp2025.Domain.Spectacol;
import ro.mpp2025.Service.BiletReservationException;
import ro.mpp2025.Service.IAppService;
import ro.mpp2025.Service.ServiceException;
import ro.mpp2025.dto.DTOFactory;
import ro.mpp2025.objectprotocol.UpdatedSpectacolResponse;
import ro.mpp2025.observer.Observer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class ProtoClientWorker implements Runnable, Observer {
    private IAppService server;
    private Socket connection;
    private InputStream input;
    private OutputStream output;
    private volatile boolean connected;

    public ProtoClientWorker(IAppService server, Socket connection) {
        this.server = server;
        this.connection = connection;
        try {
            output = connection.getOutputStream();
            input = connection.getInputStream();
            connected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (connected) {
            try {
                System.out.println("Waiting requests ...");
                FestivalProtobufs.Request request = FestivalProtobufs.Request.parseDelimitedFrom(input);  // 🟢 MODIFICAT
                System.out.println("Request received: " + request);
                FestivalProtobufs.Response response = handleRequest(request);           // 🟢 MODIFICAT
                if (response != null) {
                    sendResponse(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            input.close();
            output.close();
            connection.close();
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }

    private void sendResponse(FestivalProtobufs.Response response) throws IOException {
        System.out.println("sending response " + response);
        synchronized (output) {
            response.writeDelimitedTo(output);
            output.flush();
        }
    }
    private FestivalProtobufs.Response handleRequest(FestivalProtobufs.Request request){
        FestivalProtobufs.Response response=null;
        switch (request.getType()){
            case Login:{
                System.out.println("Login request ...");
                Angajat angajat=ProtoUtils.getAngajat(request);
                try {
                    server.loginAngajat(angajat.getUsername(), angajat.getPassword(), this);
                    return ProtoUtils.createOkResponse();
                } catch (ServiceException e) {
                    connected=false;
                    return ProtoUtils.createErrorResponse(e.getMessage());
                }
            }
            case GetSpectacole:{
                System.out.println("GetSpectacoleRequest ...");
                try {
                    var spectacole=server.getAllSpectacole();
                    return ProtoUtils.createGetSpectacoleResponse(spectacole);
                } catch (ServiceException e) {
                    return ProtoUtils.createErrorResponse(e.getMessage());
                }
            }
            case FilterSpectacole:{
                System.out.println("FilterSpectacole Request ...");
                String day=ProtoUtils.getDay(request);
                LocalDateTime d=LocalDateTime.parse(day);
                try {
                    var spectacole=server.filterSpectacole(d);
                    return ProtoUtils.createFilterSpectacoleResponse(spectacole);
                } catch (ServiceException e) {
                    return ProtoUtils.createErrorResponse(e.getMessage());
                }
            }
            case ReserveBilet:{
                System.out.println("ReserveBilet Request ...");
                try{
                    var spectacol=ProtoUtils.getBilet(request).getSpectacol();
                    String cumparatorNume=ProtoUtils.getBilet(request).getNumeCumparator();
                    int seats=ProtoUtils.getBilet(request).getNrLocuri();
                    server.reserveBilet(spectacol, cumparatorNume, seats);
                    return ProtoUtils.createOkResponse();
                }catch(BiletReservationException e){
                    return ProtoUtils.createErrorResponse(e.getMessage());
                } catch(ServiceException e){
                    return ProtoUtils.createErrorResponse(e.getMessage());
                }
            }
        }
        return response;
    }
    @Override
    public void updatedSpectacol(Spectacol s) {
        System.out.println("Worker: Updated spectacol "+ s);
        try {
            sendResponse(ProtoUtils.createUpdatedSpectacolResponse(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
