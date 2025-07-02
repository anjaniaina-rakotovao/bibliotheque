package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.LivreProfilEntity;

public interface LivreProfilRepository extends JpaRepository<LivreProfilEntity, Integer> {
}