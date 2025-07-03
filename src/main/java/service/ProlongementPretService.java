package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import entities.ProlongementPretEntity;
import repository.ProlongementPretRepository;

public class ProlongementPretService {

    private ProlongementPretRepository prolongementPretRepository;

    @Autowired
    public void setProlongementPretRepository(ProlongementPretRepository prolongementPretRepository) {
        this.prolongementPretRepository = prolongementPretRepository;
    }

    public List<ProlongementPretEntity> findAll() {
        return prolongementPretRepository.findAll();
    }

    public ProlongementPretEntity save(ProlongementPretEntity prolongement) {
        return prolongementPretRepository.save(prolongement);
    }

    public void delete(Integer id) {
        prolongementPretRepository.deleteById(id);
    }

    // public List<ProlongementPretEntity> findByPret(Integer idPret) {
    //     return prolongementPretRepository.findByPret_IdPret(idPret);
    // }
}