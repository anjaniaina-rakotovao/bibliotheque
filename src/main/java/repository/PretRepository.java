package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.PretEntity;

public interface PretRepository extends JpaRepository<PretEntity, Integer> {
}