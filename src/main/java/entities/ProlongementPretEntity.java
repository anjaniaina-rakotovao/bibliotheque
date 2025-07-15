package entities;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "prolongementPret")
public class ProlongementPretEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProlongementPret")
    private Integer idProlongementPret;

    @Column(name = "duree")
    private Integer duree;

    @Column(name = "status")
    private String status;

    @Column(name = "dateProlongement")
    private LocalDate dateProlongement;

    @ManyToOne
    @JoinColumn(name = "idPret")
    private PretEntity pret;

    public ProlongementPretEntity() {}

    public Integer getIdProlongementPret() {
        return idProlongementPret;
    }

    public void setIdProlongementPret(Integer idProlongementPret) {
        this.idProlongementPret = idProlongementPret;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public PretEntity getPret() {
        return pret;
    }

    public void setPret(PretEntity pret) {
        this.pret = pret;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDateProlongement() {
        return dateProlongement;
    }

    public void setDateProlongement(LocalDate dateProlongement) {
        this.dateProlongement = dateProlongement;
    }
}