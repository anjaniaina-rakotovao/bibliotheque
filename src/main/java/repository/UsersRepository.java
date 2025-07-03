package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.UsersEntity;
import java.util.Optional; 

public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {

    Optional<UsersEntity> findByUserNameAndMotDePasse(String userName, String motDePasse);

    Optional<UsersEntity> findByUserName(String userName);
}
