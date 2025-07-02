package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.HistoriqueCotisationEntity;

public interface HistoriqueCotisationRepository extends JpaRepository<HistoriqueCotisationEntity, Integer> {
}