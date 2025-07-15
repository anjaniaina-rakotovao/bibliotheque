package service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import utils.DateUtil;

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
import utils.DateUtil;
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

    @Autowired
    private ProlongementPretService prolongementPretService;

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

       public PretEntity creerPretPlace(AdherentEntity adherent,
            ExemplaireEntity exemplaire,
            TypePretEntity typePret,
            LocalDate datePret) {

        PretEntity pret = new PretEntity();
        pret.setAdherent(adherent);
        pret.setExemplaire(exemplaire);
        pret.setTypePret(typePret);
        pret.setDatePret(datePret);

        PretEntity saved = pretRepository.save(pret);

        StatutPretEntity statutRendu = statutPretRepository.findByStatut("Rendu")
                .orElseThrow(() -> new RuntimeException("Statut « Rendu » introuvable"));

        HistoriquePretEntity histo = new HistoriquePretEntity();
        histo.setPret(saved);
        histo.setStatut(statutRendu);
        histo.setDateStatut(datePret);

        // historiquePretRepository.save(histo);


        return saved;
    }


    @Transactional(readOnly = true)
    public long countPretsActifs(Integer idAdherent) {
        List<PretEntity> tousLesPrets = pretRepository.findByAdherent_IdAdherent(idAdherent);
        for (PretEntity p : tousLesPrets) {
            p.getHistoriques().size();
        }

        long enCours = tousLesPrets.stream()
                .filter(this::isEnCours)
                .count();

        return enCours;
    }

    public List<PretEntity> getPretsEnCoursParAdherent(Integer idAdherent) {
        return pretRepository.findByAdherent_IdAdherentAndHistoriques_Statut_Statut(
                idAdherent, "EnCours"
        );
    }

    @Transactional(readOnly = true)
    public List<PretEntity> findPretsEnCoursParAdherent(Integer idAdherent) {
        List<PretEntity> prets = pretRepository.findByAdherent_IdAdherent(idAdherent);

        for (PretEntity pret : prets) {
            pret.getHistoriques().size();
        }

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

        PretEntity pret = pretRepository.findById(idPret)
                .orElseThrow(() -> new RuntimeException("Prêt introuvable"));
        if (!isEnCours(pret)) {
            throw new RuntimeException("Ce prêt n’est plus en cours.");
        }

        if (DateUtil.isJourNonOuvre(dateRetour)) {
            LocalDate prochaine = DateUtil.prochainJourOuvre(dateRetour);
            throw new RuntimeException(
                    "Le retour doit se faire un jour ouvrable. Prochain jour ouvrable : " + prochaine);
        }

        LocalDate dateFinPrevue = calculerDateFinEffective(pret);
        LocalDate dateLimite = DateUtil.prochainJourOuvre(dateFinPrevue);

        StatutPretEntity statutRendu = statutPretRepository.findByStatut("Rendu")
                .orElseThrow(() -> new RuntimeException("Statut 'Rendu' manquant"));

        HistoriquePretEntity histo = new HistoriquePretEntity();
        histo.setPret(pret);
        histo.setStatut(statutRendu);
        histo.setDateStatut(dateRetour);
        historiquePretRepository.save(histo);

        if (dateRetour.isAfter(dateLimite)) {

            HistoriquePenaliteEntity derniere
                    = historiquePenaliteRepository
                            .findTopByAdherent_IdAdherentOrderByDateFinPenaliteDesc(
                                    pret.getAdherent().getIdAdherent());

            LocalDate dateDebut = (derniere != null)
                    ? derniere.getDateFinPenalite().plusDays(1)
                    : dateRetour;

            HistoriquePenaliteEntity pen = new HistoriquePenaliteEntity();
            pen.setAdherent(pret.getAdherent());
            pen.setDateDebutPenalite(dateDebut);
            pen.setDateFinPenalite(DateUtil.prochainJourOuvre(dateDebut.plusDays(15)));
            historiquePenaliteRepository.save(pen);
        }

        // ExemplaireEntity ex = pret.getExemplaire();
        // ex.setNbExemplaire(ex.getNbExemplaire());
        // exemplaireRepository.save(ex);
    }

    private boolean isEnCours(PretEntity pret) {
        return pret.getHistoriques().stream()
                .max(Comparator.comparing(HistoriquePretEntity::getDateStatut))
                .map(h -> h.getStatut().getIdStatut() == 1) // 1 = EnCours
                .orElse(false);
    }

    @Transactional(readOnly = true)
    public List<PretEntity> getPretsEnCoursByAdherent(Integer idAdherent) {
        List<PretEntity> tous = pretRepository.findByAdherent_IdAdherent(idAdherent);

        for (PretEntity p : tous) {
            p.getHistoriques().size();
        }

        return tous.stream()
                .filter(this::isEnCours)
                .toList();
    }

    public LocalDate calculerDateFinEffective(PretEntity pret) {
        int dureeInitiale = 28;
        LocalDate datePret = pret.getDatePret();
        LocalDate dateFin = datePret.plusDays(dureeInitiale);

        List<ProlongementPretEntity> prolongements = prolongementPretService.getDerniersProlongementsConfirmeParPret()
                .stream().filter(p -> p.getPret().getIdPret().equals(pret.getIdPret()))
                .toList();

        for (ProlongementPretEntity p : prolongements) {
            dateFin = dateFin.plusDays(p.getDuree());
        }

        return dateFin;
    }

    @Transactional
    public LocalDate prolongerPret(Integer idPret, Integer dureeJours, LocalDate dateProlongement) {

        PretEntity pret = pretRepository.findById(idPret)
                .orElseThrow(() -> new RuntimeException("Prêt introuvable"));

        if (!isEnCours(pret)) {
            throw new RuntimeException("Ce prêt n'est plus en cours.");
        }

        if (dureeJours < 1 || dureeJours > 30) {
            throw new RuntimeException("Prolongation entre 1 et 30 jours.");
        }

        ProlongementPretEntity prolong = new ProlongementPretEntity();
        String status = "attente";
        prolong.setPret(pret);
        prolong.setDuree(dureeJours);
        prolong.setDateProlongement(dateProlongement);
        prolong.setStatus(status);
        prolongementPretRepository.save(prolong);

        return calculerDateFinEffective(pret);
    }

}
