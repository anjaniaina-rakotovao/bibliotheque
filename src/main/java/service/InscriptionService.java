package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import entities.InscriptionEntity;
import repository.InscriptionRepository;

public class InscriptionService {

    private InscriptionRepository inscriptionRepository;

    @Autowired
    public void setInscriptionRepository(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    public List<InscriptionEntity> findAll() {
        return inscriptionRepository.findAll();
    }

    public InscriptionEntity save(InscriptionEntity inscription) {
        return inscriptionRepository.save(inscription);
    }

    public void delete(Integer id) {
        inscriptionRepository.deleteById(id);
    }

    public List<InscriptionEntity> findActiveSubscriptions() {
        return inscriptionRepository.findByDateFinAbonnementAfter(java.time.LocalDate.now());
    }
}