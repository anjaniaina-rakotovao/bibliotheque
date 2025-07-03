package service;

import entities.StatutPretEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.StatutPretRepository;

import java.util.Optional;

public class StatutPretService {

    @Autowired
    private StatutPretRepository statutPretRepository;

    public Optional<StatutPretEntity> findByStatut(String statut) {
        return statutPretRepository.findByStatut(statut);
    }
    
}
