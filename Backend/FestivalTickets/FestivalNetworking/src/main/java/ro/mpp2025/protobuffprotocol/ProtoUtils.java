package ro.mpp2025.protobuffprotocol;

import ro.mpp2025.Domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ProtoUtils {
    public static FestivalProtobufs.Request createLoginRequest(String username, String password){
        FestivalProtobufs.Angajat angajat=FestivalProtobufs.Angajat.newBuilder().setUsername(username).setPassword(password).build();
        FestivalProtobufs.Request request= FestivalProtobufs.Request.newBuilder().setType(FestivalProtobufs.Request.Type.Login)
                .setAngajat(angajat).build();
        return request;
    }
    public static FestivalProtobufs.Request createReserveBiletRequest(Spectacol sp,String nume,int nr){
        FestivalProtobufs.Bilet bilet=FestivalProtobufs.Bilet.newBuilder()
                .setSpectacol(toProtoSpectacol(sp))
                .setCumparatorName(nume)
                .setSeats(nr).build();
        FestivalProtobufs.Request request= FestivalProtobufs.Request.newBuilder().setType(FestivalProtobufs.Request.Type.ReserveBilet)
                .setBilet(bilet).build();
        return request;
    }
    private static FestivalProtobufs.Spectacol toProtoSpectacol(Spectacol sp) {
        return FestivalProtobufs.Spectacol.newBuilder()
                .setArtist(sp.getArtist())
                .setData(sp.getData().toString())
                .setLocatie(sp.getLocatie())
                .setNrLocuriDisponibile(sp.getNrLocuriDisponibile())
                .setNrLocuriVandute(sp.getNrLocuriVandute())
                .setId(sp.getId())
                .build();
    }
    public static FestivalProtobufs.Request createGetSpectacoleRequest(){
        FestivalProtobufs.Request request= FestivalProtobufs.Request.newBuilder().setType(FestivalProtobufs.Request.Type.GetSpectacole).build();
        return request;
    }
    public static FestivalProtobufs.Request createFilterSpectacoleRequest(String day){
        FestivalProtobufs.Request request= FestivalProtobufs.Request.newBuilder().setType(FestivalProtobufs.Request.Type.FilterSpectacole)
                .setDay(day).build();
        return request;
    }



    public static FestivalProtobufs.Response createOkResponse(){
        FestivalProtobufs.Response response=FestivalProtobufs.Response.newBuilder()
                .setType(FestivalProtobufs.Response.Type.Ok).build();
        return response;
    }

    public static FestivalProtobufs.Response createErrorResponse(String text){
        FestivalProtobufs.Response response=FestivalProtobufs.Response.newBuilder()
                .setType(FestivalProtobufs.Response.Type.Error)
                .setError(text).build();
        return response;
    }



    public static FestivalProtobufs.Response createFilterSpectacoleResponse(Iterable<Spectacol>spectacole){
        FestivalProtobufs.Response.Builder response=FestivalProtobufs.Response.newBuilder()
                .setType(FestivalProtobufs.Response.Type.FilterSpectacole);
        for (Spectacol s: spectacole){
            FestivalProtobufs.Spectacol spectacol=FestivalProtobufs.Spectacol.newBuilder()
                    .setArtist(s.getArtist())
                    .setLocatie(s.getLocatie())
                    .setData(s.getData().toString())
                    .setNrLocuriDisponibile(s.getNrLocuriDisponibile())
                    .setNrLocuriVandute(s.getNrLocuriVandute())
                    .setId(s.getId())
                    .build();
            response.addSpectacole(spectacol);
        }
        return response.build();
    }
    public static FestivalProtobufs.Response createGetSpectacoleResponse(Iterable<Spectacol> spectacole){
        FestivalProtobufs.Response.Builder response=FestivalProtobufs.Response.newBuilder()
                .setType(FestivalProtobufs.Response.Type.GetSpectacole);
        for (Spectacol s: spectacole){
            FestivalProtobufs.Spectacol spectacol=FestivalProtobufs.Spectacol.newBuilder()
                    .setArtist(s.getArtist())
                    .setLocatie(s.getLocatie())
                    .setData(s.getData().toString())
                    .setNrLocuriDisponibile(s.getNrLocuriDisponibile())
                    .setNrLocuriVandute(s.getNrLocuriVandute())
                    .setId(s.getId())
                    .build();
            response.addSpectacole(spectacol);
        }
        return response.build();
    }
    public static FestivalProtobufs.Response createUpdatedSpectacolResponse(Spectacol s){
        FestivalProtobufs.Spectacol spectacol=FestivalProtobufs.Spectacol.newBuilder()
                .setArtist(s.getArtist())
                .setLocatie(s.getLocatie())
                .setData(s.getData().toString())
                .setNrLocuriDisponibile(s.getNrLocuriDisponibile())
                .setNrLocuriVandute(s.getNrLocuriVandute())
                .setId(s.getId())
                .build();
        FestivalProtobufs.Response.Builder response= FestivalProtobufs.Response.newBuilder()
                .setType(FestivalProtobufs.Response.Type.updateSpectacol)
                .setSpectacol(spectacol);
        return response.build();
    }

    public static String getError(FestivalProtobufs.Response response){
        String errorMessage=response.getError();
        return errorMessage;
    }

    public static Iterable<Spectacol>getSpectacole(FestivalProtobufs.Response responge){
        List<Spectacol> spectacole=new ArrayList<>();
        for (int i=0;i<responge.getSpectacoleCount();i++){
            FestivalProtobufs.Spectacol sp=responge.getSpectacole(i);
            Spectacol spectacol=new Spectacol();
            spectacol.setArtist(sp.getArtist());
            spectacol.setData(LocalDateTime.parse(sp.getData()));
            spectacol.setLocatie(sp.getLocatie());
            spectacol.setNrLocuriDisponibile(sp.getNrLocuriDisponibile());
            spectacol.setNrLocuriVandute(sp.getNrLocuriVandute());
            spectacol.setId(sp.getId());
            spectacole.add(spectacol);
        }
        return spectacole;
    }
    public static Spectacol getSpectacol(FestivalProtobufs.Response response){
        Spectacol spectacol=new Spectacol();
        FestivalProtobufs.Spectacol sp=response.getSpectacol();
        spectacol.setArtist(sp.getArtist());
        spectacol.setData(LocalDateTime.parse(sp.getData()));
        spectacol.setLocatie(sp.getLocatie());
        spectacol.setNrLocuriDisponibile(sp.getNrLocuriDisponibile());
        spectacol.setNrLocuriVandute(sp.getNrLocuriVandute());
        spectacol.setId(sp.getId());
        return spectacol;
    }
    public static Angajat getAngajat(FestivalProtobufs.Response request){
        Angajat angajat=new Angajat();
        angajat.setUsername(request.getAngajat().getUsername());
        angajat.setPassword(request.getAngajat().getPassword());
        return angajat;
    }
    public static Angajat getAngajat(FestivalProtobufs.Request request){
        Angajat angajat=new Angajat();
        angajat.setUsername(request.getAngajat().getUsername());
        angajat.setPassword(request.getAngajat().getPassword());
        return angajat;
    }
    public static String getDay(FestivalProtobufs.Request request){
        String day=request.getDay();
        return day;
    }
    public static Bilet getBilet(FestivalProtobufs.Request request){
        Bilet bilet=new Bilet();
        bilet.setSpectacol(fromProtoSpectacol(request.getBilet().getSpectacol()));
        bilet.setNumeCumparator(request.getBilet().getCumparatorName());
        bilet.setNrLocuri(request.getBilet().getSeats());
        return bilet;
    }
    private static Spectacol fromProtoSpectacol(FestivalProtobufs.Spectacol proto) {
        Spectacol spectacol = new Spectacol();
        spectacol.setArtist(proto.getArtist());
        spectacol.setData(LocalDateTime.parse(proto.getData()));
        spectacol.setLocatie(proto.getLocatie());
        spectacol.setNrLocuriDisponibile(proto.getNrLocuriDisponibile());
        spectacol.setNrLocuriVandute(proto.getNrLocuriVandute());
        spectacol.setId(proto.getId());
        return spectacol;
    }



}
