package entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "inscription")
public class InscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInscription")
    private Integer idInscription;

    @Column(name = "dateDebutAbonnement")
    private LocalDate dateDebutAbonnement;

    @Column(name = "dateFinAbonnement")
    private LocalDate dateFinAbonnement;

    @OneToMany(mappedBy = "inscription")
    private List<AdherentEntity> adherents;

    public InscriptionEntity() {}

    public Integer getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(Integer idInscription) {
        this.idInscription = idInscription;
    }

    public LocalDate getDateDebutAbonnement() {
        return dateDebutAbonnement;
    }

    public void setDateDebutAbonnement(LocalDate dateDebutAbonnement) {
        this.dateDebutAbonnement = dateDebutAbonnement;
    }

    public LocalDate getDateFinAbonnement() {
        return dateFinAbonnement;
    }

    public void setDateFinAbonnement(LocalDate dateFinAbonnement) {
        this.dateFinAbonnement = dateFinAbonnement;
    }

    public List<AdherentEntity> getAdherents() {
        return adherents;
    }

    public void setAdherents(List<AdherentEntity> adherents) {
        this.adherents = adherents;
    }
}