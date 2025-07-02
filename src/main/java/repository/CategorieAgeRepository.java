package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.CategorieAgeEntity;

public interface CategorieAgeRepository extends JpaRepository<CategorieAgeEntity, Integer> {
}