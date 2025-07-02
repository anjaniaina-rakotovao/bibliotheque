package entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "HistoriquePret")
public class HistoriquePretEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHistoriquePret")
    private Integer idHistoriquePret;

    @Column(name = "dateStatut")
    private LocalDate dateStatut;

    @ManyToOne
    @JoinColumn(name = "idStatut")
    private StatutPretEntity statut;

    @ManyToOne
    @JoinColumn(name = "idPret")
    private PretEntity pret;

    public HistoriquePretEntity() {}

    public Integer getIdHistoriquePret() {
        return idHistoriquePret;
    }

    public void setIdHistoriquePret(Integer idHistoriquePret) {
        this.idHistoriquePret = idHistoriquePret;
    }

    public LocalDate getDateStatut() {
        return dateStatut;
    }

    public void setDateStatut(LocalDate dateStatut) {
        this.dateStatut = dateStatut;
    }

    public StatutPretEntity getStatut() {
        return statut;
    }

    public void setStatut(StatutPretEntity statut) {
        this.statut = statut;
    }

    public PretEntity getPret() {
        return pret;
    }

    public void setPret(PretEntity pret) {
        this.pret = pret;
    }
}