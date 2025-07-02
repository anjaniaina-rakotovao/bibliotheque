package service;

import java.util.List;
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
}