package repository;

import entities.ExemplaireEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExemplaireRepository extends JpaRepository<ExemplaireEntity, Integer> {

    /** Tous les exemplaires d’un livre donné */
    List<ExemplaireEntity> findByLivre_IdLivre(Integer idLivre);
}
