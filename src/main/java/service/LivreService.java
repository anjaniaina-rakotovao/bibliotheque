package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import entities.LivreEntity;
import repository.LivreRepository;

public class LivreService {

    private LivreRepository livreRepository;

    @Autowired
    public void setLivreRepository(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    public List<LivreEntity> findAll() {
        return livreRepository.findAll();
    }

    public LivreEntity save(LivreEntity livre) {
        return livreRepository.save(livre);
    }

    public void delete(Integer id) {
        livreRepository.deleteById(id);
    }

    // public List<LivreEntity> findByCategorieAge(Integer idCategorie) {
    //     return livreRepository.findByCategorieAge_IdCategorieAge(idCategorie);
    // }
}