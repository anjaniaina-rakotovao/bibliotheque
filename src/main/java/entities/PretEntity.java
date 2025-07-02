package entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pret")
public class PretEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPret")
    private Integer idPret;

    @Column(name = "datePret")
    private LocalDate datePret;

    @ManyToOne
    @JoinColumn(name = "idExemplaire")
    private ExemplaireEntity exemplaire;

    @ManyToOne
    @JoinColumn(name = "idAdherent")
    private AdherentEntity adherent;

    @ManyToOne
    @JoinColumn(name = "idTypePret")
    private TypePretEntity typePret;

    @OneToMany(mappedBy = "pret")
    private List<HistoriquePretEntity> historiques;

    @OneToMany(mappedBy = "pret")
    private List<ProlongementPretEntity> prolongements;

    public PretEntity() {}

    public Integer getIdPret() {
        return idPret;
    }

    public void setIdPret(Integer idPret) {
        this.idPret = idPret;
    }

    public LocalDate getDatePret() {
        return datePret;
    }

    public void setDatePret(LocalDate datePret) {
        this.datePret = datePret;
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

    public TypePretEntity getTypePret() {
        return typePret;
    }

    public void setTypePret(TypePretEntity typePret) {
        this.typePret = typePret;
    }

    public List<HistoriquePretEntity> getHistoriques() {
        return historiques;
    }

    public void setHistoriques(List<HistoriquePretEntity> historiques) {
        this.historiques = historiques;
    }

    public List<ProlongementPretEntity> getProlongements() {
        return prolongements;
    }

    public void setProlongements(List<ProlongementPretEntity> prolongements) {
        this.prolongements = prolongements;
    }
}