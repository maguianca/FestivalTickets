package ro.mpp2025.Domain;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "Spectacol")
public class Spectacol implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "artist")
    private String artist;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @Column(name = "data", updatable = false)
    private LocalDateTime data;
    @Column(name = "locatie")
    private String locatie;
    @Column(name = "nrLocuriDisponibile")
    private int nrLocuriDisponibile;
    @Column(name = "nrLocuriVandute")
    private int nrLocuriVandute;

    public Spectacol(){
        this.artist = "";
        this.data = LocalDateTime.now();
        this.locatie = "";
        this.nrLocuriDisponibile = 0;
        this.nrLocuriVandute = 0;
    }
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public int getNrLocuriDisponibile() {
        return nrLocuriDisponibile;
    }

    public void setNrLocuriDisponibile(int nrLocuriDisponibile) {
        this.nrLocuriDisponibile = nrLocuriDisponibile;
    }

    public int getNrLocuriVandute() {
        return nrLocuriVandute;
    }

    public void setNrLocuriVandute(int nrLocuriVandute) {
        this.nrLocuriVandute = nrLocuriVandute;
    }

    public Spectacol(String artist, LocalDateTime data, String locatie, int nrLocuriDisponibile, int nrLocuriVandute) {
        this.artist = artist;
        this.data = data;
        this.locatie = locatie;
        this.nrLocuriDisponibile = nrLocuriDisponibile;
        this.nrLocuriVandute = nrLocuriVandute;
    }

    @Override
    public String toString() {
        return "Spectacol{" +
                "artist='" + artist + '\'' +
                ", data=" + data +
                ", locatie='" + locatie + '\'' +
                ", nrLocuriDisponibile=" + nrLocuriDisponibile +
                ", nrLocuriVandute=" + nrLocuriVandute +
                '}';
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer integer) {
        this.id=integer;
    }
}
