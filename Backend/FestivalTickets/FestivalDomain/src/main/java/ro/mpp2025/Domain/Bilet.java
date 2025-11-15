package ro.mpp2025.Domain;
import jakarta.persistence.*;

@Entity
@Table(name = "Bilet")
public class Bilet implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "numeCumparator")
    private String numeCumparator;
    @Column(name = "nrLocuri")
    private int nrLocuri;
    @ManyToOne
    @JoinColumn(name = "spectacol", referencedColumnName = "id")
    private Spectacol spectacol;

    public Bilet() {
        this.numeCumparator = "";
        this.nrLocuri = 0;
        this.spectacol = new Spectacol();
    }
    public Spectacol getSpectacol() {
        return spectacol;
    }

    public void setSpectacol(Spectacol spectacol) {
        this.spectacol = spectacol;
    }
    public String getNumeCumparator() {
        return numeCumparator;
    }

    public void setNumeCumparator(String numeCumparator) {
        this.numeCumparator = numeCumparator;
    }

    public int getNrLocuri() {
        return nrLocuri;
    }

    public void setNrLocuri(int nrLocuri) {
        this.nrLocuri = nrLocuri;
    }

    public Bilet(String numeCumparator, int nrLocuri, Spectacol spectacol) {
        this.numeCumparator = numeCumparator;
        this.nrLocuri = nrLocuri;
        this.spectacol = spectacol;
    }

    @Override
    public String toString() {
        return "Bilet{" +
                "numeCumparator='" + numeCumparator + '\'' +
                ", nrLocuri=" + nrLocuri +
                ", spectacol=" + spectacol +
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
