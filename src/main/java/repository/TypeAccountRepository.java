package repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import entities.TypeAccountEntity;

public interface TypeAccountRepository extends JpaRepository<TypeAccountEntity, Integer> {
    Optional<TypeAccountEntity> findByAccountType(String accountType);
    boolean existsByAccountType(String accountType);
}