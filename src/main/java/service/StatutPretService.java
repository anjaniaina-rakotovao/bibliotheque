package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import entities.StatutPretEntity;
import repository.StatutPretRepository;

public class StatutPretService {

    private StatutPretRepository statutPretRepository;

    @Autowired
    public void setStatutPretRepository(StatutPretRepository statutPretRepository) {
        this.statutPretRepository = statutPretRepository;
    }

    public List<StatutPretEntity> findAll() {
        return statutPretRepository.findAll();
    }

    public StatutPretEntity save(StatutPretEntity statut) {
        return statutPretRepository.save(statut);
    }

    public void delete(Integer id) {
        statutPretRepository.deleteById(id);
    }

    // public StatutPretEntity findByStatut(String statut) {
    //     return statutPretRepository.findByStatut(statut);
    // }
}