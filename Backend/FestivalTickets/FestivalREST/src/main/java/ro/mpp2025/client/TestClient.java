package ro.mpp2025.client;

import ro.mpp2025.Domain.Spectacol;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestClient {
    private static final String BASE_URL = "http://localhost:8080/festival/spectacole";

    public static void main(String[] args) {
        RestClient client = RestClient.create();

        String datetime = "2026-10-19 19:50:00.000";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime data = LocalDateTime.parse(datetime, formatter);
        //add
        Spectacol spectacol = new Spectacol("Java", data, "Sesiune", 200, 0);
        Spectacol created = client.post()
                .uri(BASE_URL)
                .body(spectacol)
                .retrieve()
                .body(Spectacol.class);
        System.out.println("*******************Add spectacol: " + created);
        //getAll
        Spectacol[] all = client.get()
                .uri(BASE_URL)
                .retrieve()
                .body(Spectacol[].class);

        System.out.println("\n******************All spectacole:");
        for (Spectacol s : all) {
            System.out.println(s);
        }
        //findOne
        if (created != null && created.getId() != null) {
            Spectacol found = client.get()
                    .uri(BASE_URL + "/" + created.getId())
                    .retrieve()
                    .body(Spectacol.class);

            System.out.println("\n***************Spectacol found ID=" + created.getId());
            System.out.println(found);

            //update
            found.setLocatie("******************Loc nou: Aula Magna");
            found.setNrLocuriDisponibile(300);
            found.setNrLocuriVandute(100);

            client.put()
                    .uri(String.format("%s/%d", BASE_URL, found.getId()))
                    .body(found)
                    .retrieve()
                    .toBodilessEntity();

            System.out.println("******************Spectacol updated.");
            //findOne
            Spectacol updated = client.get()
                    .uri(BASE_URL + "/" + found.getId())
                    .retrieve()
                    .body(Spectacol.class);

            System.out.println("*******************Spectacol after update:");
            System.out.println(updated);

            //delete
            client.delete()
                    .uri(BASE_URL + "/" + found.getId())
                    .retrieve()
                    .toBodilessEntity();

            System.out.println("*******************Spectacol deleted ID" + found.getId());
            //getAll
            Spectacol[] all2 = client.get()
                    .uri(BASE_URL)
                    .retrieve()
                    .body(Spectacol[].class);

            System.out.println("\n*****************All spectacole:");
            for (Spectacol s : all2) {
                System.out.println(s);
            }
        }
    }
}
