package service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import entities.*;

import repository.*;

public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private HistoriqueReservationRepository historiqueReservationRepository;

    @Autowired
    private HistoriquePretService historiquePretService;

    @Autowired
    private PretService pretService;

    @Autowired
    public void setReservationRepository(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationEntity> findAll() {
        return reservationRepository.findAll();
    }

    public ReservationEntity save(ReservationEntity reservation) {
        return reservationRepository.save(reservation);
    }

    public void delete(Integer id) {
        reservationRepository.deleteById(id);
    }

    public void confirmReservation(Integer idReservation, LocalDate dateValidation) {
        ReservationEntity r = reservationRepository.findById(idReservation).orElse(null);
        HistoriqueReservationEntity r2 = new HistoriqueReservationEntity();
        r2.setDateMouvement(dateValidation);
        r2.setReservation(r);
        r2.setStatut("confirm");

        historiqueReservationRepository.save(r2);

        TypePretEntity t = new TypePretEntity();
        t.setIdTypePret(1);
        PretEntity p = new PretEntity();
        p.setAdherent(r.getAdherent());
        p.setDatePret(r.getDateReservation());
        p.setExemplaire(r.getExemplaire());
        p.setTypePret(t);

        pretService.save(p);

        StatutPretEntity statutEnCours = new StatutPretEntity();
        statutEnCours.setIdStatut(1); 
        HistoriquePretEntity historique = new HistoriquePretEntity();
        historique.setPret(p);
        historique.setStatut(statutEnCours);
        historique.setDateStatut(r.getDateReservation());
        historiquePretService.save(historique);
    }
}
