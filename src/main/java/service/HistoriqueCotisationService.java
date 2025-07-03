package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import entities.HistoriqueCotisationEntity;
import repository.HistoriqueCotisationRepository;

public class HistoriqueCotisationService {

    private HistoriqueCotisationRepository historiqueCotisationRepository;

    @Autowired
    public void setHistoriqueCotisationRepository(HistoriqueCotisationRepository historiqueCotisationRepository) {
        this.historiqueCotisationRepository = historiqueCotisationRepository;
    }

    public List<HistoriqueCotisationEntity> findAll() {
        return historiqueCotisationRepository.findAll();
    }

    public HistoriqueCotisationEntity save(HistoriqueCotisationEntity historique) {
        return historiqueCotisationRepository.save(historique);
    }

    public void delete(Integer id) {
        historiqueCotisationRepository.deleteById(id);
    }
}