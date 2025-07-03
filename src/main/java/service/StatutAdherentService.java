package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import entities.StatutAdherentEntity;
import repository.StatutAdherentRepository;

public class StatutAdherentService {

    private StatutAdherentRepository statutAdherentRepository;

    @Autowired
    public void setStatutAdherentRepository(StatutAdherentRepository statutAdherentRepository) {
        this.statutAdherentRepository = statutAdherentRepository;
    }

    public List<StatutAdherentEntity> findAll() {
        return statutAdherentRepository.findAll();
    }

    public StatutAdherentEntity save(StatutAdherentEntity statut) {
        return statutAdherentRepository.save(statut);
    }

    public void delete(Integer id) {
        statutAdherentRepository.deleteById(id);
    }

    // public StatutAdherentEntity findByStatut(String statut) {
    //     return statutAdherentRepository.findByStatut(statut);
    // }
}