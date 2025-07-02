package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.AdherentEntity;

public interface AdherentRepository extends JpaRepository<AdherentEntity, Integer> {
}