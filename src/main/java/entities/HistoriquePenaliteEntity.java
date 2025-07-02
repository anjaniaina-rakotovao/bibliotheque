package entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "historiquePenalite")
public class HistoriquePenaliteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHistoriquePenalite")
    private Integer idHistoriquePenalite;

    @Column(name = "dateDebutPenalite")
    private LocalDate dateDebutPenalite;

    @Column(name = "dateFinPenalite")
    private LocalDate dateFinPenalite;

    @ManyToOne
    @JoinColumn(name = "idAdherent")
    private AdherentEntity adherent;

    public HistoriquePenaliteEntity() {}

    public Integer getIdHistoriquePenalite() {
        return idHistoriquePenalite;
    }

    public void setIdHistoriquePenalite(Integer idHistoriquePenalite) {
        this.idHistoriquePenalite = idHistoriquePenalite;
    }

    public LocalDate getDateDebutPenalite() {
        return dateDebutPenalite;
    }

    public void setDateDebutPenalite(LocalDate dateDebutPenalite) {
        this.dateDebutPenalite = dateDebutPenalite;
    }

    public LocalDate getDateFinPenalite() {
        return dateFinPenalite;
    }

    public void setDateFinPenalite(LocalDate dateFinPenalite) {
        this.dateFinPenalite = dateFinPenalite;
    }

    public AdherentEntity getAdherent() {
        return adherent;
    }

    public void setAdherent(AdherentEntity adherent) {
        this.adherent = adherent;
    }
}