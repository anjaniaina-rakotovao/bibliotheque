package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import entities.HistoriquePenaliteEntity;
import repository.HistoriquePenaliteRepository;

public class HistoriquePenaliteService {

    private HistoriquePenaliteRepository historiquePenaliteRepository;

    @Autowired
    public void setHistoriquePenaliteRepository(HistoriquePenaliteRepository historiquePenaliteRepository) {
        this.historiquePenaliteRepository = historiquePenaliteRepository;
    }

    public List<HistoriquePenaliteEntity> findAll() {
        return historiquePenaliteRepository.findAll();
    }

    public HistoriquePenaliteEntity save(HistoriquePenaliteEntity historique) {
        return historiquePenaliteRepository.save(historique);
    }

    public void delete(Integer id) {
        historiquePenaliteRepository.deleteById(id);
    }

    // public List<HistoriquePenaliteEntity> findByAdherent(Integer idAdherent) {
    //     return historiquePenaliteRepository.findByAdherent_IdAdherent(idAdherent);
    // }
}