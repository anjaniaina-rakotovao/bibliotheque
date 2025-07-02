package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.TagLivreEntity;

public interface TagLivreRepository extends JpaRepository<TagLivreEntity, Integer> {
}