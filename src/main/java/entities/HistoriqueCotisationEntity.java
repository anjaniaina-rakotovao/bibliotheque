package entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "historiqueCotisation")
public class HistoriqueCotisationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHistoriqueCotisation")
    private Integer idHistoriqueCotisation;

    @Column(name = "dateCotisation")
    private LocalDate dateCotisation;

    @ManyToOne
    @JoinColumn(name = "idAdherent")
    private AdherentEntity adherent;

    public HistoriqueCotisationEntity() {}

    public Integer getIdHistoriqueCotisation() {
        return idHistoriqueCotisation;
    }

    public void setIdHistoriqueCotisation(Integer idHistoriqueCotisation) {
        this.idHistoriqueCotisation = idHistoriqueCotisation;
    }

    public LocalDate getDateCotisation() {
        return dateCotisation;
    }

    public void setDateCotisation(LocalDate dateCotisation) {
        this.dateCotisation = dateCotisation;
    }

    public AdherentEntity getAdherent() {
        return adherent;
    }

    public void setAdherent(AdherentEntity adherent) {
        this.adherent = adherent;
    }
}