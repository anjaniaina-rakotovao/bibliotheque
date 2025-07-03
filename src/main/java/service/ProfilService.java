package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import entities.ProfilEntity;
import repository.ProfilRepository;

public class ProfilService {

    private ProfilRepository profilRepository;

    @Autowired
    public void setProfilRepository(ProfilRepository profilRepository) {
        this.profilRepository = profilRepository;
    }

    public List<ProfilEntity> findAll() {
        return profilRepository.findAll();
    }

    public ProfilEntity save(ProfilEntity profil) {
        return profilRepository.save(profil);
    }

    public void delete(Integer id) {
        profilRepository.deleteById(id);
    }

    public ProfilEntity findByType(String profilType) {
        return profilRepository.findByProfilType(profilType);
    }
}