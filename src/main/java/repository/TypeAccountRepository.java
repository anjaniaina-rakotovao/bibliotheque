package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.TypeAccountEntity;

public interface TypeAccountRepository extends JpaRepository<TypeAccountEntity, Integer>{
    
}

