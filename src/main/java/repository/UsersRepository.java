package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.UsersEntity;

public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {

    Optional<UsersEntity> findByUserNameAndMotDePasse(String userName, String motDePasse);

    Optional<UsersEntity> findByUserName(String userName);
}
