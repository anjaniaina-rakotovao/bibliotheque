package entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "statutPret")
public class StatutPretEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idStatut")
    private Integer idStatut;

    @Column(name = "statut")
    private String statut;

    @OneToMany(mappedBy = "statut")
    private List<HistoriquePretEntity> historiques;

    public StatutPretEntity() {}

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

    public List<HistoriquePretEntity> getHistoriques() {
        return historiques;
    }

    public void setHistoriques(List<HistoriquePretEntity> historiques) {
        this.historiques = historiques;
    }
}