package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.InscriptionEntity;

public interface InscriptionRepository extends JpaRepository<InscriptionEntity, Integer> {
}