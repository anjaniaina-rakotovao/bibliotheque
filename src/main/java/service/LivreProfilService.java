package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import entities.LivreProfilEntity;
import repository.LivreProfilRepository;

public class LivreProfilService {

    private LivreProfilRepository livreProfilRepository;

    @Autowired
    public void setLivreProfilRepository(LivreProfilRepository livreProfilRepository) {
        this.livreProfilRepository = livreProfilRepository;
    }

    public List<LivreProfilEntity> findAll() {
        return livreProfilRepository.findAll();
    }

    public LivreProfilEntity save(LivreProfilEntity livreProfil) {
        return livreProfilRepository.save(livreProfil);
    }

    public void delete(Integer id) {
        livreProfilRepository.deleteById(id);
    }

    public List<LivreProfilEntity> findByProfil(Integer idProfil) {
        return livreProfilRepository.findByProfil_IdProfil(idProfil);
    }

    public List<LivreProfilEntity> findByLivre(Integer idLivre) {
        return livreProfilRepository.findByLivre_IdLivre(idLivre);
    }
}