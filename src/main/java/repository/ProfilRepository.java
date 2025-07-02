package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.ProfilEntity;

public interface ProfilRepository extends JpaRepository<ProfilEntity, Integer> {
}