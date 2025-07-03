package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import entities.HistoriquePretEntity;
import repository.HistoriquePretRepository;

public class HistoriquePretService {

    private HistoriquePretRepository historiquePretRepository;

    @Autowired
    public void setHistoriquePretRepository(HistoriquePretRepository historiquePretRepository) {
        this.historiquePretRepository = historiquePretRepository;
    }

    public List<HistoriquePretEntity> findAll() {
        return historiquePretRepository.findAll();
    }

    public HistoriquePretEntity save(HistoriquePretEntity historique) {
        return historiquePretRepository.save(historique);
    }

    public void delete(Integer id) {
        historiquePretRepository.deleteById(id);
    }

    // public List<HistoriquePretEntity> findByPret(Integer idPret) {
    //     return historiquePretRepository.findByPret_IdPret(idPret);
    // }
}