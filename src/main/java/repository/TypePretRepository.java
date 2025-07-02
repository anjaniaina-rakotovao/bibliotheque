package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.TypePretEntity;

public interface TypePretRepository extends JpaRepository<TypePretEntity, Integer> {
}