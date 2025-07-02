package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.ProlongementPretEntity;

public interface ProlongementPretRepository extends JpaRepository<ProlongementPretEntity, Integer> {
}