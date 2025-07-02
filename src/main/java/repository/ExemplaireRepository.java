package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.ExemplaireEntity;

public interface ExemplaireRepository extends JpaRepository<ExemplaireEntity, Integer> {
}