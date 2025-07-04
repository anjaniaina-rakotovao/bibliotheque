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
import entities.ProlongementPretEntity;
import entities.StatutPretEntity;
import entities.TypePretEntity;
import repository.AdherentRepository;
import repository.ExemplaireRepository;
import repository.PretRepository;
import repository.StatutPretRepository;
import repository.HistoriquePenaliteRepository;
import repository.HistoriquePretRepository;
import repository.ProlongementPretRepository;

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

    @Autowired
    private ProlongementPretRepository prolongementPretRepository;

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

    // @Transactional
    // public void effectuerRetour(Integer idPret, LocalDate dateRetour) {
    //     PretEntity pret = pretRepository.findById(idPret)
    //             .orElseThrow(() -> new RuntimeException("Prêt introuvable"));
    //     /* --- Vérifier qu’il est encore EnCours --- */
    //     if (!isEnCours(pret)) {
    //         throw new RuntimeException("Ce prêt n’est plus en cours.");
    //     }
    //     /* --- Calcul du délai --- */
    //     long jours = ChronoUnit.DAYS.between(pret.getDatePret(), dateRetour);
    //     /* --- Ajouter l’historique “Rendu” --- */
    //     StatutPretEntity statutRendu = statutPretRepository.findByStatut("Rendu")
    //             .orElseThrow(() -> new RuntimeException("Statut 'Rendu' manquant"));
    //     HistoriquePretEntity histo = new HistoriquePretEntity();
    //     histo.setPret(pret);
    //     histo.setStatut(statutRendu);
    //     histo.setDateStatut(dateRetour);
    //     historiquePretRepository.save(histo);
    //     /* --- Pénalité si > 28 jours --- */
    //     if (jours > 28) {
    //         HistoriquePenaliteEntity pen = new HistoriquePenaliteEntity();
    //         pen.setAdherent(pret.getAdherent());
    //         pen.setDateDebutPenalite(dateRetour);
    //         pen.setDateFinPenalite(dateRetour.plusDays(15));   // ex : 15 jours de blocage
    //         historiquePenaliteRepository.save(pen);
    //     }
    //     /* --- Exemplaire de nouveau disponible ( si tu gères un stock ) --- */
    //     ExemplaireEntity ex = pret.getExemplaire();
    //     ex.setNbExemplaire(ex.getNbExemplaire() + 1);
    //     exemplaireRepository.save(ex);
    // }
    @Transactional
    public void effectuerRetour(Integer idPret, LocalDate dateRetour) {

        PretEntity pret = pretRepository.findById(idPret)
                .orElseThrow(() -> new RuntimeException("Prêt introuvable"));

        /* --- Vérifier qu’il est encore EnCours --- */
        if (!isEnCours(pret)) {
            throw new RuntimeException("Ce prêt n’est plus en cours.");
        }

        /* --- Calculer la date de fin effective (incluant prolongements) --- */
        LocalDate dateFinEffective = calculerDateFinEffective(pret);

        /* --- Calcul du délai entre la date de fin effective et la date de retour --- */
        long joursRetard = ChronoUnit.DAYS.between(dateFinEffective, dateRetour);

        /* --- Ajouter l’historique “Rendu” --- */
        StatutPretEntity statutRendu = statutPretRepository.findByStatut("Rendu")
                .orElseThrow(() -> new RuntimeException("Statut 'Rendu' manquant"));

        HistoriquePretEntity histo = new HistoriquePretEntity();
        histo.setPret(pret);
        histo.setStatut(statutRendu);
        histo.setDateStatut(dateRetour);
        historiquePretRepository.save(histo);

        /* --- Pénalité si retard (joursRetard > 0) --- */
        if (joursRetard > 0) {
            HistoriquePenaliteEntity pen = new HistoriquePenaliteEntity();
            pen.setAdherent(pret.getAdherent());
            pen.setDateDebutPenalite(dateRetour);
            pen.setDateFinPenalite(dateRetour.plusDays(15));   // ex : 15 jours de blocage
            historiquePenaliteRepository.save(pen);
        }

        /* --- Exemplaire de nouveau disponible --- */
        ExemplaireEntity ex = pret.getExemplaire();
        ex.setNbExemplaire(ex.getNbExemplaire() + 1);
        exemplaireRepository.save(ex);
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
                .map(h -> h.getStatut().getIdStatut() == 1) // 1 = EnCours
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

    public LocalDate calculerDateFinEffective(PretEntity pret) {
        // 1. durée initiale selon le type de prêt
        int dureeInitiale = 28;
        LocalDate datePret = pret.getDatePret();
        LocalDate dateFin = datePret.plusDays(dureeInitiale);

        // 2. ajouter les prolongements si existants
        List<ProlongementPretEntity> prolongements = prolongementPretRepository.findAll()
                .stream().filter(p -> p.getPret().getIdPret().equals(pret.getIdPret()))
                .toList();

        for (ProlongementPretEntity p : prolongements) {
            dateFin = dateFin.plusDays(p.getDuree());
        }

        return dateFin;
    }

    @Transactional
    public LocalDate prolongerPret(Integer idPret, Integer dureeJours) {

        PretEntity pret = pretRepository.findById(idPret)
                .orElseThrow(() -> new RuntimeException("Prêt introuvable"));

        if (!isEnCours(pret)) {
            throw new RuntimeException("Ce prêt n'est plus en cours.");
        }

        if (dureeJours < 1 || dureeJours > 30) {
            throw new RuntimeException("Prolongation entre 1 et 30 jours.");
        }

        // Historiser la prolongation
        ProlongementPretEntity prolong = new ProlongementPretEntity();
        prolong.setPret(pret);
        prolong.setDuree(dureeJours);
        prolongementPretRepository.save(prolong);

        // Retourner la nouvelle date de fin (en recalculant)
        return calculerDateFinEffective(pret);
    }

}
