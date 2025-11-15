package ro.mpp2025.protobuffprotocol;

import ro.mpp2025.Domain.Angajat;
import ro.mpp2025.Domain.Spectacol;
import ro.mpp2025.Service.IAppService;
import ro.mpp2025.Service.ServiceException;
import ro.mpp2025.dto.DTOFactory;
import ro.mpp2025.objectprotocol.*;
import ro.mpp2025.observer.Observer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class ProtoServiceProxy implements IAppService {
    private String host;
    private int port;
    private Observer client;
    private InputStream input;
    private OutputStream output;
    private Socket connection;

    private BlockingQueue<FestivalProtobufs.Response>qresponses;
    private volatile boolean finished;

    public ProtoServiceProxy(String host, int port) {
        this.host = host;
        this.port = port;
        qresponses=new LinkedBlockingQueue<FestivalProtobufs.Response>();
    }


    @Override
    public Angajat loginAngajat(String username, String password, Observer client) throws ServiceException {
        initializeConnection();
        sendRequest(ProtoUtils.createLoginRequest(username, password));
        FestivalProtobufs.Response response= readResponse();
        this.client=client;

        Angajat angajat = null;
        /*if(response.getType()==FestivalProtobufs.Response.Type.Ok){
            System.out.println("Hello");
            #angajat=
        }*/
        if(response.getType()==FestivalProtobufs.Response.Type.Error){
            String errorText=ProtoUtils.getError(response);
            closeConnection();
            throw new ServiceException(errorText);
        }
        angajat=ProtoUtils.getAngajat(response);
        return angajat;
    }

    @Override
    public void registerAngajat(String username,String password,String email) throws ServiceException {
        //nu
    }

    @Override
    public Iterable<Spectacol> getAllSpectacole() throws ServiceException {
        sendRequest(ProtoUtils.createGetSpectacoleRequest());
        FestivalProtobufs.Response response=readResponse();
        if(response.getType()==FestivalProtobufs.Response.Type.Error){
            String errorText=ProtoUtils.getError(response);
            closeConnection();
            throw new ServiceException(errorText);
        }
        Iterable<Spectacol>sp=ProtoUtils.getSpectacole(response);
        return sp;

    }

    @Override
    public Iterable<Spectacol> filterSpectacole(LocalDateTime startDate, LocalDateTime endDate) {
      return null;
    }
    @Override
    public Iterable<Spectacol>filterSpectacole(LocalDateTime day)throws ServiceException {
        sendRequest(ProtoUtils.createFilterSpectacoleRequest(day.toString()));
        FestivalProtobufs.Response response=readResponse();
        if(response.getType()==FestivalProtobufs.Response.Type.Error){
            String errorText=ProtoUtils.getError(response);
            closeConnection();
            throw new ServiceException(errorText);
        }
        Iterable<Spectacol>sp=ProtoUtils.getSpectacole(response);
        return sp;
    }
    @Override
    public void reserveBilet(Spectacol spectacol,String cumparatorNume,int seats)throws ServiceException{
        sendRequest(ProtoUtils.createReserveBiletRequest(spectacol,cumparatorNume,seats));
        FestivalProtobufs.Response response=readResponse();
        if(response.getType()==FestivalProtobufs.Response.Type.Error){
            String errorText=ProtoUtils.getError(response);
            closeConnection();
            throw new ServiceException(errorText);
        }
    }
    @Override
    public void logout(Angajat angajat){
        closeConnection();
    }


    private void initializeConnection(){
        try {
            connection=new Socket(host,port);
            output=connection.getOutputStream();
            input=connection.getInputStream();
            finished=false;
            startReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void startReader(){
        Thread tw=new Thread(new ReaderThread());
        tw.start();
    }
    private void sendRequest(FestivalProtobufs.Request request)throws ServiceException{
        try {
            System.out.println("Sending request ..."+request);
            //request.writeTo(output);
            request.writeDelimitedTo(output);
            output.flush();
            System.out.println("Request sent.");
        } catch (IOException e) {
            throw new ServiceException("Error sending object "+e);
        }

    }

    private FestivalProtobufs.Response readResponse() throws ServiceException{
        FestivalProtobufs.Response response=null;
        try{
            response=qresponses.take();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }
    private class ReaderThread implements Runnable{
        public void run() {
            while(!finished){
                try {
                    FestivalProtobufs.Response response=FestivalProtobufs.Response.parseDelimitedFrom(input);
                    System.out.println("response received "+response);

                    if (response.getType()==FestivalProtobufs.Response.Type.updateSpectacol){
                        handleUpdate(response);
                    }else{
                        try {
                            qresponses.put(response);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Reading error "+e);
                }
            }
        }
    }

    protected void handleUpdate(FestivalProtobufs.Response response){
            Spectacol spectacol =ProtoUtils.getSpectacol(response);
            System.out.println("Handle:Updated spectacol "+spectacol);
            try {
                client.updatedSpectacol(spectacol);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    private void closeConnection() {
        finished=true;
        try {
            input.close();
            output.close();
            connection.close();
            client=null;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
