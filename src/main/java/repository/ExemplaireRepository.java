package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.ExemplaireEntity;
import java.util.List;


public interface ExemplaireRepository extends JpaRepository<ExemplaireEntity, Integer> {
      List<ExemplaireEntity> findByLivre_IdLivre(Integer idLivre);
}