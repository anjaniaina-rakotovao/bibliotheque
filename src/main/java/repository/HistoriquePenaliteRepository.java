package repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.HistoriquePenaliteEntity;

public interface HistoriquePenaliteRepository extends JpaRepository<HistoriquePenaliteEntity, Integer> {

    boolean existsByAdherent_IdAdherentAndDateDebutPenaliteLessThanEqualAndDateFinPenaliteGreaterThanEqual(
            Integer idAdherent, LocalDate dateDebut, LocalDate dateFin);

    HistoriquePenaliteEntity
            findTopByAdherent_IdAdherentOrderByDateFinPenaliteDesc(Integer idAdherent);

    List<HistoriquePenaliteEntity> findByAdherentIdAdherentOrderByDateFinPenaliteDesc(Integer idAdherent);

}
