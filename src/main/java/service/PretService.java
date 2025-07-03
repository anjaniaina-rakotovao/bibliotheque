package service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import entities.AdherentEntity;
import entities.CategorieAgeEntity;
import entities.ExemplaireEntity;
import entities.HistoriquePretEntity;
import entities.LivreEntity;
import entities.PretEntity;
import entities.StatutPretEntity;
import entities.TypePretEntity;
import repository.AdherentRepository;
import repository.ExemplaireRepository;
import repository.PretRepository;
import repository.StatutPretRepository;

public class PretService {

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private StatutPretRepository statutPretRepository;

    public PretEntity save(PretEntity pret) {
        return pretRepository.save(pret);
    }

    public PretEntity creerPret(AdherentEntity adherent,
            ExemplaireEntity exemplaire,
            TypePretEntity typePret,
            LocalDate datePret) {

        PretEntity pret = new PretEntity();
        pret.setAdherent(adherent);
        pret.setExemplaire(exemplaire);
        pret.setTypePret(typePret);
        pret.setDatePret(datePret);

        PretEntity saved = pretRepository.save(pret);

        /* ---- Historique « EnCours » ---- */
        StatutPretEntity statutEnCours = statutPretRepository.findByStatut("EnCours")
                .orElseThrow(() -> new RuntimeException("Statut « EnCours » introuvable"));

        HistoriquePretEntity histo = new HistoriquePretEntity();
        histo.setPret(saved);
        histo.setStatut(statutEnCours);
        histo.setDateStatut(datePret);
        // si HistoriquePretRepository existe :
        // historiquePretRepository.save(histo);
        // sinon laisser la cascade si mappée

        return saved;
    }

public long countPretsActifs(Integer idAdherent) {
    return pretRepository.countByAdherent_IdAdherentAndHistoriques_Statut_Statut(
        idAdherent, "EnCours"
    );
}
    // Méthode utilitaire pour calculer l'âge
    private int calculateAge(LocalDate naissance, LocalDate dateRef) {
        if (naissance == null || dateRef == null) {
            return 0;
        }
        return dateRef.getYear() - naissance.getYear()
                - (dateRef.getDayOfYear() < naissance.getDayOfYear() ? 1 : 0);
    }

 
}