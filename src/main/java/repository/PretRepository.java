package repository;

import entities.PretEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PretRepository extends JpaRepository<PretEntity, Integer> {

    List<PretEntity> findByAdherent_IdAdherentAndHistoriques_Statut_Statut(
            Integer idAdherent, String statut);

    List<PretEntity> findByExemplaire_IdExemplaireAndHistoriques_Statut_Statut(
            Integer idExemplaire, String statut);

    long countByAdherent_IdAdherentAndHistoriques_Statut_Statut(
            Integer idAdherent, String statut);

    long countByExemplaire_IdExemplaireAndHistoriques_Statut_Statut(
            Integer idExemplaire, String statut);

    List<PretEntity> findByAdherent_IdAdherent(Integer idAdherent);

}
