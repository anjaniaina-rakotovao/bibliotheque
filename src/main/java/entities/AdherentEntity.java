package entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "adherent")
public class AdherentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAdherent")
    private Integer idAdherent;

    @Column(name = "adherentName")
    private String adherentName;

    @ManyToOne
    @JoinColumn(name = "idProfil")
    private ProfilEntity profil;

    @ManyToOne
    @JoinColumn(name = "idInscription")
    private InscriptionEntity inscription;

    @ManyToOne
    @JoinColumn(name = "statut")
    private StatutAdherentEntity statut;

    @Column(name = "dateNaissance")  // Nouveau champ
    private LocalDate dateNaissance;

    @OneToMany(mappedBy = "adherent")
    private List<PretEntity> prets;

    @OneToMany(mappedBy = "adherent")
    private List<ReservationEntity> reservations;

    @OneToMany(mappedBy = "adherent")
    private List<HistoriqueStatutAdherentEntity> historiqueStatuts;

    public AdherentEntity() {}

    public Integer getIdAdherent() {
        return idAdherent;
    }

    public void setIdAdherent(Integer idAdherent) {
        this.idAdherent = idAdherent;
    }

    public String getAdherentName() {
        return adherentName;
    }

    public void setAdherentName(String adherentName) {
        this.adherentName = adherentName;
    }

    public ProfilEntity getProfil() {
        return profil;
    }

    public void setProfil(ProfilEntity profil) {
        this.profil = profil;
    }

    public InscriptionEntity getInscription() {
        return inscription;
    }

    public void setInscription(InscriptionEntity inscription) {
        this.inscription = inscription;
    }

    public StatutAdherentEntity getStatut() {
        return statut;
    }

    public void setStatut(StatutAdherentEntity statut) {
        this.statut = statut;
    }

    public List<PretEntity> getPrets() {
        return prets;
    }

    public void setPrets(List<PretEntity> prets) {
        this.prets = prets;
    }

    public List<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationEntity> reservations) {
        this.reservations = reservations;
    }

    public List<HistoriqueStatutAdherentEntity> getHistoriqueStatuts() {
        return historiqueStatuts;
    }

    public void setHistoriqueStatuts(List<HistoriqueStatutAdherentEntity> historiqueStatuts) {
        this.historiqueStatuts = historiqueStatuts;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
}