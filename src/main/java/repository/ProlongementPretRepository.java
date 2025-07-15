package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.ProlongementPretEntity;

public interface ProlongementPretRepository extends JpaRepository<ProlongementPretEntity, Integer> {

    List<ProlongementPretEntity> findByPret_IdPret(Integer idPret);

}
