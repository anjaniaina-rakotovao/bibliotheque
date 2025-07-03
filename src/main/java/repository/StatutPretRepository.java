package repository;

import entities.StatutPretEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatutPretRepository extends JpaRepository<StatutPretEntity, Integer> {

    /** Recherche par libellé (ex. "EnCours", "Rendu") */
    Optional<StatutPretEntity> findByStatut(String statut);
    
}
