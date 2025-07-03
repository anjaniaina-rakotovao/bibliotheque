package repository;

import entities.HistoriquePenaliteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HistoriquePenaliteRepository extends JpaRepository<HistoriquePenaliteEntity, Integer> {

    /** Toutes les pénalités d’un adhérent */
    List<HistoriquePenaliteEntity> findByAdherent_IdAdherent(Integer idAdherent);

    /**
     * Vérifie s’il existe au moins une pénalité couvrant la date passée
     * (utile pour refuser un prêt si la pénalité est encore active).
     */
    boolean existsByAdherent_IdAdherentAndDateDebutPenaliteLessThanEqualAndDateFinPenaliteGreaterThanEqual(
            Integer idAdherent, LocalDate dateDebutIncluse, LocalDate dateFinIncluse);
}
