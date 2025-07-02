package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import entities.PretEntity;
import repository.PretRepository;

public class PretService {

    private PretRepository pretRepository;

    @Autowired
    public void setPretRepository(PretRepository pretRepository) {
        this.pretRepository = pretRepository;
    }

    public List<PretEntity> findAll() {
        return pretRepository.findAll();
    }

    public PretEntity save(PretEntity pret) {
        return pretRepository.save(pret);
    }

    public void delete(Integer id) {
        pretRepository.deleteById(id);
    }
}