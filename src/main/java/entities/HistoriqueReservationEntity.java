package entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "historiqueReservation")
public class HistoriqueReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHistoriqueReservation")
    private Integer idHistoriqueReservation;

    @ManyToOne
    @JoinColumn(name = "idReservation")
    private ReservationEntity reservation;

    @Column(name = "dateMouvement")
    private LocalDate dateMouvement;

    @Column(name = "statut")
    private String statut;

    public Integer getIdHistoriqueReservation() {
        return idHistoriqueReservation;
    }

    public void setIdHistoriqueReservation(Integer idHistoriqueReservation) {
        this.idHistoriqueReservation = idHistoriqueReservation;
    }

    public ReservationEntity getReservation() {
        return reservation;
    }

    public void setReservation(ReservationEntity reservation) {
        this.reservation = reservation;
    }

    public LocalDate getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(LocalDate dateMouvement) {
        this.dateMouvement = dateMouvement;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

 
}
