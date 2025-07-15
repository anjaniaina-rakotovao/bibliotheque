package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.HistoriqueReservationEntity;

public interface HistoriqueReservationRepository extends JpaRepository<HistoriqueReservationEntity, Integer> {
}