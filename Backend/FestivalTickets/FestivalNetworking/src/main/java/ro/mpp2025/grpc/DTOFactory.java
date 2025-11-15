package ro.mpp2025.grpc;

import ro.mpp2025.Domain.Spectacol;

import java.time.LocalDateTime;

public class DTOFactory {

    public static Spectacol fromGrpcSpectacol(Festivalgrpc.Spectacol grpcSpectacol) {
        Spectacol sp= new Spectacol(grpcSpectacol.getArtist(),
                LocalDateTime.parse(grpcSpectacol.getData()),
                grpcSpectacol.getLocatie(),
                grpcSpectacol.getNrLocuriDisponibile(),
                grpcSpectacol.getNrLocuriVandute());
        sp.setId(grpcSpectacol.getId());
        return sp;
    }

    public static Festivalgrpc.Spectacol toGrpcSpectacol(Spectacol s) {
        return Festivalgrpc.Spectacol.newBuilder()
                .setId(s.getId())
                .setArtist(s.getArtist())
                .setData(s.getData().toString())
                .setLocatie(s.getLocatie())
                .setNrLocuriDisponibile(s.getNrLocuriDisponibile())
                .setNrLocuriVandute(s.getNrLocuriVandute())
                .build();
    }

}
