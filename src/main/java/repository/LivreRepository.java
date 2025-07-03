package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.LivreEntity;

public interface LivreRepository extends JpaRepository<LivreEntity, Integer> {
    
}