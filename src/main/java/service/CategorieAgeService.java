package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import entities.CategorieAgeEntity;
import repository.CategorieAgeRepository;

public class CategorieAgeService {

    private CategorieAgeRepository categorieAgeRepository;

    @Autowired
    public void setCategorieAgeRepository(CategorieAgeRepository categorieAgeRepository) {
        this.categorieAgeRepository = categorieAgeRepository;
    }

    public List<CategorieAgeEntity> findAll() {
        return categorieAgeRepository.findAll();
    }

    public CategorieAgeEntity save(Integer ageMin, Integer ageMax) {
        CategorieAgeEntity categorie = new CategorieAgeEntity();
        categorie.setAgeMin(ageMin);
        categorie.setAgeMax(ageMax);
        return categorieAgeRepository.save(categorie);
    }

    public void delete(Integer id) {
        categorieAgeRepository.deleteById(id);
    }
}