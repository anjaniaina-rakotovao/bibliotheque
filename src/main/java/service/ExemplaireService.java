package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import entities.ExemplaireEntity;
import repository.ExemplaireRepository;
import entities.PretEntity;
import repository.PretRepository;

public class ExemplaireService {

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private PretRepository pretRepository;

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
        return null;
    }
}
