package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import entities.ExemplaireEntity;
import entities.HistoriquePretEntity;
import entities.LivreEntity;
import entities.PretEntity;
import repository.ExemplaireRepository;
import repository.LivreRepository;
import repository.PretRepository;
import java.util.Optional;

public class LivreService {

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private PretRepository pretRepository;

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

    public LivreEntity findById(Integer id) {
        return livreRepository.findById(id).orElse(null);
    }

    // public List<LivreEntity> findByCategorieAge(Integer idCategorie) {
    //     return livreRepository.findByCategorieAge_IdCategorieAge(idCategorie);
    // }
    public List<Map<String, Object>> getLivresAvecNombreExemplaires() {
        List<LivreEntity> livres = livreRepository.findAll();
        List<ExemplaireEntity> exemplaires = exemplaireRepository.findAll();

        List<Map<String, Object>> result = new ArrayList<>();

        for (LivreEntity livre : livres) {
            ExemplaireEntity ex = exemplaires.stream()
                    .filter(e -> e.getLivre().getIdLivre().equals(livre.getIdLivre()))
                    .findFirst()
                    .orElse(null);

            if (ex != null) {

                long empruntes = pretRepository
                        .countByExemplaire_IdExemplaireAndHistoriques_Statut_Statut(
                                ex.getIdExemplaire(), "EnCours");

                int nbRestant = ex.getNbExemplaire() - (int) empruntes;

                Map<String, Object> map = new HashMap<>();
                map.put("idLivre", livre.getIdLivre());
                map.put("titre", livre.getTitre());
                map.put("nbExemplaire", ex.getNbExemplaire());
                map.put("nbRestant", nbRestant);
                map.put("disponible", nbRestant > 0);

                result.add(map);
            }
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> getLivresAvecStatistiques() {
        List<ExemplaireEntity> exemplaires = exemplaireRepository.findAll();
        List<Map<String, Object>> resultat = new ArrayList<>();

        for (ExemplaireEntity ex : exemplaires) {
            LivreEntity livre = ex.getLivre();

            List<PretEntity> prets = pretRepository.findByExemplaire_IdExemplaire(ex.getIdExemplaire());

            int nbEmpruntes = 0;
            for (PretEntity pret : prets) {
                Optional<HistoriquePretEntity> dernier = pret.getHistoriques().stream()
                        .max(Comparator.comparing(HistoriquePretEntity::getDateStatut));
                if (dernier.isPresent() && "EnCours".equalsIgnoreCase(dernier.get().getStatut().getStatut())) {
                    nbEmpruntes++;
                }
            }

            int nbRestant = ex.getNbExemplaire() - nbEmpruntes;

            Map<String, Object> ligne = new HashMap<>();
            ligne.put("idLivre", livre.getIdLivre());
            ligne.put("titre", livre.getTitre());
            ligne.put("idExemplaire", ex.getIdExemplaire());
            ligne.put("nbExemplaire", ex.getNbExemplaire());
            ligne.put("nbEmpruntes", nbEmpruntes);
            ligne.put("nbRestant", nbRestant);
            ligne.put("disponible", nbRestant > 0);

            resultat.add(ligne);
        }

        return resultat;
    }

}
