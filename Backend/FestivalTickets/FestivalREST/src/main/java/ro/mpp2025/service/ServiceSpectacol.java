package ro.mpp2025.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ro.mpp2025.Domain.Spectacol;
import ro.mpp2025.Repository.BiletDbRepo;
import ro.mpp2025.Repository.EntityRepoException;
import ro.mpp2025.Repository.SpectacolDbRepo;
import ro.mpp2025.Repository.AngajatDbRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class ServiceSpectacol implements IService {
    @Autowired
    private SpectacolDbRepo repositorySpectacol;
    @Autowired
    private BiletDbRepo repositoryBilet;
    private Logger logger = LogManager.getLogger();

    @Override
    public Spectacol addSpectacol(String data, String locatie, int nrLocuriDisponibile, int nrLocuriVandute, String artist) throws EntityRepoException {
        logger.traceEntry();
        try {
            Spectacol spectacol = new Spectacol(artist, LocalDateTime.parse(data), locatie, nrLocuriDisponibile, nrLocuriVandute);
            repositorySpectacol.add(spectacol);
            return repositorySpectacol.getByParams(artist, LocalDateTime.parse(data), locatie,nrLocuriDisponibile,nrLocuriVandute);
        } catch (Exception e) {
            logger.error(e);
            System.out.println("Error adding spectacol " + e);
        }

        return null;

    }

    @Override
    public Spectacol updateSpectacol(Integer id, String data, String locatie, int nrLocuriDisponibile, int nrLocuriVandute, String artist) throws EntityRepoException {
        logger.traceEntry();
        Spectacol spectacol = repositorySpectacol.getById(id);
        if (spectacol != null) {
            try {
                spectacol.setData(LocalDateTime.parse(data));
                spectacol.setLocatie(locatie);
                spectacol.setNrLocuriDisponibile(nrLocuriDisponibile);
                spectacol.setNrLocuriVandute(nrLocuriVandute);
                spectacol.setArtist(artist);
                spectacol.setId(id);
                repositorySpectacol.update(spectacol);
                return spectacol;
            } catch (Exception e) {
                logger.error(e);
                System.out.println("Error updating spectacol " + e);
            }
        } else {
            logger.error("Spectacolul nu exista");
            System.out.println("Spectacolul nu exista");
        }
        return null;
    }

    @Override
    public Spectacol deleteSpectacol(Integer id) throws EntityRepoException {
        logger.traceEntry();
        Spectacol spectacol = repositorySpectacol.getById(id);
        if (spectacol != null) {
            try {
                repositorySpectacol.remove(id);
                return spectacol;
            } catch (Exception e) {
                logger.error(e);
                System.out.println("Error deleting spectacol " + e);
            }
        } else {
            logger.error("Spectacolul nu exista");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Spectacolul cu id-ul " + id + " nu a fost găsit");

        }
        return null;
    }

    @Override
    public List<Spectacol> getAllSpectacole() throws EntityRepoException {
        logger.traceEntry();
        return (List<Spectacol>) repositorySpectacol.getAll();
    }

    @Override
    public Spectacol getSpectacol(Integer id) throws EntityRepoException {
        logger.traceEntry();
        return repositorySpectacol.getById(id);
    }
}
