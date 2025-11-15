package ro.mpp2025;

import ro.mpp2025.Domain.Spectacol;
import ro.mpp2025.Repository.*;
import ro.mpp2025.Server.ObjectConcurrentServer;
import ro.mpp2025.Server.ServerException;
import ro.mpp2025.Server.ServiceImplementation;
import ro.mpp2025.Service.*;
import ro.mpp2025.grpc.GrpcServiceImpl;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import ro.mpp2025.grpc.GrpcServiceImpl;

public class Main {

    public static void main(String[] args) throws ServerException, IOException, InterruptedException {
        var props = loadProperties();
        /*var angajatRepo = new AngajatDbRepo(props);
        var spectacolRepo =new SpectacolDbRepo(props);
        var biletRepo =new BiletDbRepo(props, spectacolRepo);*/

        var angajatRepo = new AngajatDbRepoHibernate();
        var spectacolRepo = new SpectacolDbRepoHibernate();
        var biletRepo = new BiletDbRepoHibernate();

        var angajatService = new AngajatService(angajatRepo);
        var biletService = new BiletService(biletRepo);
        var spectacolService = new SpectacolService(spectacolRepo);

        var appService = new AppService(angajatService, biletService, spectacolService);
        //var server = new ObjectConcurrentServer(15000, new ServiceImplementation(appService));
        //server.start();
        Server grpcServer = ServerBuilder
                .forPort(15000)
                .addService(new GrpcServiceImpl(appService))
                .build();

        System.out.println("Starting gRPC server on port 15000...");
        grpcServer.start();
        grpcServer.awaitTermination();

        for (Spectacol s : spectacolRepo.getAll()) {
            System.out.println("Spectacol: " + s);
        }

    }

    public static Properties loadProperties() {
        Properties props=new Properties();
        try {
            props.load(new FileReader("bd.conifg"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }
        return props;
    }
}