package service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import entities.AdherentEntity;
import entities.HistoriquePenaliteEntity;
import repository.AdherentRepository;
import repository.HistoriquePenaliteRepository;

public class AdherentService {

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private HistoriquePenaliteRepository historiquePenaliteRepository;

    @Autowired                       // ← AJOUT
    private PretService pretService;

    /* --- CRUD simples --- */
    public List<AdherentEntity> findAll() {
        return adherentRepository.findAll();
    }

    public AdherentEntity save(AdherentEntity a) {
        return adherentRepository.save(a);
    }

    public void delete(Integer id) {
        adherentRepository.deleteById(id);
    }

    /* --- manquant : findById --- */
    public AdherentEntity findById(Integer id) {
        return adherentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adhérent introuvable"));
    }

//     public boolean aUnePenaliteActive(Integer idAdh, LocalDate date) {
//         return historiquePenaliteRepository
//                 .existsByAdherent_IdAdherentAndDateDebutPenaliteLessThanEqualAndDateFinPenaliteGreaterThanEqual(
//                         idAdh, date, date);
//     }
//     long nbActifs = pretService.countPretsActifs(idAdherent);
//     public boolean quotaDepasse(Integer idAdherent) {
//     long nbActifs = pretService.countPretsActifs(idAdherent);
//     int quota     = adherentRepository.findById(idAdherent)
//                           .orElseThrow(() -> new RuntimeException("Adhérent introuvable"))
//                           .getProfil().getQuotaPret();
//     return nbActifs >= quota;
// }
    public boolean aUnePenaliteActive(Integer idAdh, LocalDate date) {
        return historiquePenaliteRepository
                .existsByAdherent_IdAdherentAndDateDebutPenaliteLessThanEqualAndDateFinPenaliteGreaterThanEqual(
                        idAdh, date, date);
    }

    /**
     * Vrai si le quota de prêts “EnCours” est atteint
     */
    public boolean quotaDepasse(Integer idAdh) {
        long nbActifs = pretService.countPretsActifs(idAdh);  // ← OK
        int quota = findById(idAdh).getProfil().getQuotaPret();
        return nbActifs >= quota;
    }

}
