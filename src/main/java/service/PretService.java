package service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import entities.AdherentEntity;
import entities.CategorieAgeEntity;
import entities.ExemplaireEntity;
import entities.HistoriquePenaliteEntity;
import entities.HistoriquePretEntity;
import entities.LivreEntity;
import entities.PretEntity;
import entities.StatutPretEntity;
import entities.TypePretEntity;
import repository.AdherentRepository;
import repository.ExemplaireRepository;
import repository.PretRepository;
import repository.StatutPretRepository;
import repository.HistoriquePenaliteRepository;
import repository.HistoriquePretRepository;

public class PretService {

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private StatutPretRepository statutPretRepository;

    @Autowired
    private HistoriquePenaliteRepository historiquePenaliteRepository;

    @Autowired
    private HistoriquePretRepository historiquePretRepository;

    public PretEntity save(PretEntity pret) {
        return pretRepository.save(pret);
    }

    public PretEntity findById(Integer idPret) {
        return pretRepository.findById(idPret)
                .orElseThrow(() -> new RuntimeException("Prêt introuvable avec l’ID : " + idPret));
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

    public List<PretEntity> getPretsEnCoursParAdherent(Integer idAdherent) {
        return pretRepository.findByAdherent_IdAdherentAndHistoriques_Statut_Statut(
                idAdherent, "EnCours"
        );
    }

    @Transactional(readOnly = true)
    public List<PretEntity> findPretsEnCoursParAdherent(Integer idAdherent) {
        List<PretEntity> prets = pretRepository.findByAdherent_IdAdherent(idAdherent);

        // Forcer chargement de la collection historiques pour éviter LazyInitializationException
        for (PretEntity pret : prets) {
            pret.getHistoriques().size();
        }

        // Filtrer prêts dont l'historique contient le statut "EnCours"
        List<PretEntity> pretsEnCours = new ArrayList<>();
        for (PretEntity pret : prets) {
            for (HistoriquePretEntity hist : pret.getHistoriques()) {
                if ("EnCours".equals(hist.getStatut().getStatut())) {
                    pretsEnCours.add(pret);
                    break;
                }
            }
        }

        return pretsEnCours;
    }

    @Transactional
    public void effectuerRetour(Integer idPret, LocalDate dateRetour) {
        PretEntity pret = findById(idPret);

        if (!isEnCours(pret)) {
            throw new RuntimeException("Ce prêt n'est pas en cours ou déjà retourné.");
        }

        // délai depuis la date de prêt
        long jours = ChronoUnit.DAYS.between(pret.getDatePret(), dateRetour);

        if (jours > 28) {
            // Pénalise l’adhérent (7 jours d’exemple)
            HistoriquePenaliteEntity pen = new HistoriquePenaliteEntity();
            pen.setAdherent(pret.getAdherent());
            pen.setDateDebutPenalite(dateRetour);
            pen.setDateFinPenalite(dateRetour.plusDays(7));
            historiquePenaliteRepository.save(pen);
        }

        // Ajout historique « Rendu »
        StatutPretEntity statutRendu = statutPretRepository.findByStatut("Rendu")
                .orElseThrow(() -> new RuntimeException("Statut 'Rendu' non configuré"));

        HistoriquePretEntity histo = new HistoriquePretEntity();
        histo.setPret(pret);
        histo.setStatut(statutRendu);
        histo.setDateStatut(dateRetour);
        historiquePretRepository.save(histo);
    }

    // Méthode utilitaire pour calculer l'âge
    private int calculateAge(LocalDate naissance, LocalDate dateRef) {
        if (naissance == null || dateRef == null) {
            return 0;
        }
        return dateRef.getYear() - naissance.getYear()
                - (dateRef.getDayOfYear() < naissance.getDayOfYear() ? 1 : 0);
    }

    // public boolean isEnCours(PretEntity pret) {
    //     if (pret.getHistoriques() == null || pret.getHistoriques().isEmpty()) {
    //         return false;
    //     }

    //     // Dernier statut = celui avec la date la plus récente
    //     HistoriquePretEntity dernier = pret.getHistoriques().stream()
    //             .max(Comparator.comparing(HistoriquePretEntity::getDateStatut))
    //             .orElse(null);

    //     // Statut ID = 1 → "EnCours"
    //     return dernier != null && dernier.getStatut().getIdStatut() == 1;
    // }

    private boolean isEnCours(PretEntity pret) {
    return pret.getHistoriques().stream()
        .max(Comparator.comparing(HistoriquePretEntity::getDateStatut))
        .map(h -> h.getStatut().getIdStatut() == 1)   // 1 = EnCours
        .orElse(false);
}


    @Transactional(readOnly = true)
    public List<PretEntity> getPretsEnCoursByAdherent(Integer idAdherent) {
        List<PretEntity> tous = pretRepository.findByAdherent_IdAdherent(idAdherent);

        // Force le chargement des historiques (évite LazyInitializationException)
        for (PretEntity p : tous) {
            p.getHistoriques().size();
        }

        return tous.stream()
                .filter(this::isEnCours) // garde seulement les statuts "EnCours"
                .toList();
    }
}
