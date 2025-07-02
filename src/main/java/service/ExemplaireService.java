package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import entities.ExemplaireEntity;
import repository.ExemplaireRepository;

public class ExemplaireService {

    private ExemplaireRepository exemplaireRepository;

    @Autowired
    public void setExemplaireRepository(ExemplaireRepository exemplaireRepository) {
        this.exemplaireRepository = exemplaireRepository;
    }

    public List<ExemplaireEntity> findAll() {
        return exemplaireRepository.findAll();
    }

    public ExemplaireEntity save(ExemplaireEntity exemplaire) {
        return exemplaireRepository.save(exemplaire);
    }

    public void delete(Integer id) {
        exemplaireRepository.deleteById(id);
    }
}