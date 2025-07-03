package service;

import java.util.List;
import java.util.Optional; 
import org.springframework.beans.factory.annotation.Autowired;
import entities.UsersEntity;
import repository.UsersRepository;

public class UsersService {

    private UsersRepository usersRepository;

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<UsersEntity> findAll() {
        return usersRepository.findAll();
    }

    public UsersEntity save(UsersEntity user) {
        return usersRepository.save(user);
    }

    public void delete(Integer id) {
        usersRepository.deleteById(id);
    }

    public boolean authenticate(String username, String password) {
        Optional<UsersEntity> user = usersRepository.findByUserNameAndMotDePasse(username, password);
        return user.isPresent();
    }

    // Nouvelle méthode pour récupérer l'utilisateur
    public Optional<UsersEntity> getUserByCredentials(String username, String password) {
        return usersRepository.findByUserNameAndMotDePasse(username, password);
    }

    // Méthode pour trouver par username seulement
    public Optional<UsersEntity> findByUsername(String username) {
        return usersRepository.findByUserName(username);
    }
}