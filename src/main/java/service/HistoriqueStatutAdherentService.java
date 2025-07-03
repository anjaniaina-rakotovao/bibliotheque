package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import entities.HistoriqueStatutAdherentEntity;
import repository.HistoriqueStatutAdherentRepository;

public class HistoriqueStatutAdherentService {

    private HistoriqueStatutAdherentRepository historiqueStatutAdherentRepository;

    @Autowired
    public void setHistoriqueStatutAdherentRepository(HistoriqueStatutAdherentRepository historiqueStatutAdherentRepository) {
        this.historiqueStatutAdherentRepository = historiqueStatutAdherentRepository;
    }

    public List<HistoriqueStatutAdherentEntity> findAll() {
        return historiqueStatutAdherentRepository.findAll();
    }

    public HistoriqueStatutAdherentEntity save(HistoriqueStatutAdherentEntity historique) {
        return historiqueStatutAdherentRepository.save(historique);
    }

    public void delete(Integer id) {
        historiqueStatutAdherentRepository.deleteById(id);
    }

    public List<HistoriqueStatutAdherentEntity> findByAdherent(Integer idAdherent) {
        return historiqueStatutAdherentRepository.findByAdherent_IdAdherent(idAdherent);
    }
}