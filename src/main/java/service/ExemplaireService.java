package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import entities.ExemplaireEntity;
import repository.ExemplaireRepository;
import repository.PretRepository;

public class ExemplaireService {

    private ExemplaireRepository exemplaireRepository;

    @Autowired
    public void setExemplaireRepository(ExemplaireRepository exemplaireRepository) {
        this.exemplaireRepository = exemplaireRepository;
    }
    @Autowired
    private PretRepository pretRepository;

    public List<ExemplaireEntity> findAll() {
        return exemplaireRepository.findAll();
    }

    public ExemplaireEntity save(ExemplaireEntity exemplaire) {
        return exemplaireRepository.save(exemplaire);
    }

    public void delete(Integer id) {
        exemplaireRepository.deleteById(id);
    }

        public ExemplaireEntity getExemplaireDisponible(Integer idLivre) {
        List<ExemplaireEntity> liste = exemplaireRepository.findByLivre_IdLivre(idLivre);
        for (ExemplaireEntity ex : liste) {
            long empruntes = pretRepository
                    .countByExemplaire_IdExemplaireAndHistoriques_Statut_Statut(
                            ex.getIdExemplaire(), "EnCours");
            if (ex.getNbExemplaire() > empruntes) {
                return ex;
            }
        }
        return null; // aucun disponible

        
    }

}