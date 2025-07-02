package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.StatutAdherentEntity;

public interface StatutAdherentRepository extends JpaRepository<StatutAdherentEntity, Integer> {
}