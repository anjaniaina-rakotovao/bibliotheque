package entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "historiqueStatutAdherent")
public class HistoriqueStatutAdherentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHistoriqueStatutAdherent")
    private Integer idHistoriqueStatutAdherent;

    @ManyToOne
    @JoinColumn(name = "idStatut")
    private StatutAdherentEntity statut;

    @ManyToOne
    @JoinColumn(name = "idAdherent")
    private AdherentEntity adherent;

    @Column(name = "dateChangement")
    private LocalDate dateChangement;

    public HistoriqueStatutAdherentEntity() {}

    public Integer getIdHistoriqueStatutAdherent() {
        return idHistoriqueStatutAdherent;
    }

    public void setIdHistoriqueStatutAdherent(Integer idHistoriqueStatutAdherent) {
        this.idHistoriqueStatutAdherent = idHistoriqueStatutAdherent;
    }

    public StatutAdherentEntity getStatut() {
        return statut;
    }

    public void setStatut(StatutAdherentEntity statut) {
        this.statut = statut;
    }

    public AdherentEntity getAdherent() {
        return adherent;
    }

    public void setAdherent(AdherentEntity adherent) {
        this.adherent = adherent;
    }

    public LocalDate getDateChangement() {
        return dateChangement;
    }

    public void setDateChangement(LocalDate dateChangement) {
        this.dateChangement = dateChangement;
    }
}