package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.HistoriquePenaliteEntity;

public interface HistoriquePenaliteRepository extends JpaRepository<HistoriquePenaliteEntity, Integer> {
}