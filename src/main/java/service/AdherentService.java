package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import entities.AdherentEntity;
import repository.AdherentRepository;

public class AdherentService {

    private AdherentRepository adherentRepository;

    @Autowired
    public void setAdherentRepository(AdherentRepository adherentRepository) {
        this.adherentRepository = adherentRepository;
    }

    public List<AdherentEntity> findAll() {
        return adherentRepository.findAll();
    }

    public AdherentEntity save(AdherentEntity adherent) {
        return adherentRepository.save(adherent);
    }

    public void delete(Integer id) {
        adherentRepository.deleteById(id);
    }
}