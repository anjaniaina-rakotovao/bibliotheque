package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.ReservationEntity;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {
}