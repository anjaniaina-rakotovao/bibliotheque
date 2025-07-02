package entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReservation")
    private Integer idReservation;

    @ManyToOne
    @JoinColumn(name = "idExemplaire")
    private ExemplaireEntity exemplaire;

    @ManyToOne
    @JoinColumn(name = "idAdherent")
    private AdherentEntity adherent;

    @Column(name = "dateReservation")
    private LocalDate dateReservation;

    @Column(name = "statut")
    private String statut;

    @Column(name = "expiration")
    private LocalDate expiration;

    public ReservationEntity() {}

    public Integer getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }

    public ExemplaireEntity getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(ExemplaireEntity exemplaire) {
        this.exemplaire = exemplaire;
    }

    public AdherentEntity getAdherent() {
        return adherent;
    }

    public void setAdherent(AdherentEntity adherent) {
        this.adherent = adherent;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }
}