package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.HistoriquePretEntity;

public interface HistoriquePretRepository extends JpaRepository<HistoriquePretEntity, Integer> {
}