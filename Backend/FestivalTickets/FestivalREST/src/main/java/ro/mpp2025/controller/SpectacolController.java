package ro.mpp2025.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ro.mpp2025.Domain.Spectacol;
import ro.mpp2025.Repository.EntityRepoException;
import ro.mpp2025.Service.IAppService;
import ro.mpp2025.service.IService;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
//@CrossOrigin
@RestController
@RequestMapping("/festival/spectacole")
public class SpectacolController {

    @Autowired
    private IService serviceSpectacol;

    @RequestMapping(method=RequestMethod.POST)
    public Spectacol addSpectacol(@RequestBody Spectacol spectacol) throws EntityRepoException {
        return serviceSpectacol.addSpectacol(
                spectacol.getData().toString(),
                spectacol.getLocatie(),
                spectacol.getNrLocuriDisponibile(),
                spectacol.getNrLocuriVandute(),
                spectacol.getArtist()
        );
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Spectacol updateSpectacol(@PathVariable Integer id, @RequestBody Spectacol spectacol) throws EntityRepoException {
        if (!id.equals(spectacol.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID-ul din URL nu corespunde cu ID-ul din body.");
        }
        Spectacol updated = serviceSpectacol.updateSpectacol(
                id,
                spectacol.getData().toString(),
                spectacol.getLocatie(),
                spectacol.getNrLocuriDisponibile(),
                spectacol.getNrLocuriVandute(),
                spectacol.getArtist()
        );

        if (updated == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Spectacolul nu a fost gasit.");
        }

        return updated;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Spectacol deleteSpectacol(@PathVariable Integer id) throws EntityRepoException {
        return serviceSpectacol.deleteSpectacol(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Spectacol> getAllSpectacole() throws EntityRepoException {
        return serviceSpectacol.getAllSpectacole();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Spectacol getSpectacol(@PathVariable Integer id) throws EntityRepoException {
        return serviceSpectacol.getSpectacol(id);
    }
}
