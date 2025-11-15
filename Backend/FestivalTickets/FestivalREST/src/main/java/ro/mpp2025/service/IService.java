package ro.mpp2025.service;

import ro.mpp2025.Domain.Spectacol;
import ro.mpp2025.Repository.EntityRepoException;

import java.util.List;

public interface IService {
    Spectacol addSpectacol(String data, String locatie, int nrLocuriDisponibile, int nrLocuriVandute, String artist)throws EntityRepoException;
    Spectacol updateSpectacol(Integer id, String data, String locatie, int nrLocuriDisponibile, int nrLocuriVandute, String artist)throws EntityRepoException;
    Spectacol deleteSpectacol(Integer id)throws EntityRepoException;
    List<Spectacol> getAllSpectacole()throws EntityRepoException;
    Spectacol getSpectacol(Integer id) throws EntityRepoException;

}