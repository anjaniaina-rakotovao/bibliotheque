package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.LivreProfilEntity;
import java.util.List;

public interface LivreProfilRepository extends JpaRepository<LivreProfilEntity, Integer> {
    List<LivreProfilEntity> findByLivre_IdLivre(Integer idLivre);
}