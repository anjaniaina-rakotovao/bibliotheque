package entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "statutAdherent")
public class StatutAdherentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idStatut")
    private Integer idStatut;

    @Column(name = "statut")
    private String statut;

    @OneToMany(mappedBy = "statut")
    private List<AdherentEntity> adherents;

    @OneToMany(mappedBy = "statut")
    private List<HistoriqueStatutAdherentEntity> historiques;

    public StatutAdherentEntity() {}

    public Integer getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(Integer idStatut) {
        this.idStatut = idStatut;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public List<AdherentEntity> getAdherents() {
        return adherents;
    }

    public void setAdherents(List<AdherentEntity> adherents) {
        this.adherents = adherents;
    }

    public List<HistoriqueStatutAdherentEntity> getHistoriques() {
        return historiques;
    }

    public void setHistoriques(List<HistoriqueStatutAdherentEntity> historiques) {
        this.historiques = historiques;
    }
}