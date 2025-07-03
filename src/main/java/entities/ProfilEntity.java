package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "profil")
public class ProfilEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProfil")
    private Integer idProfil;

    @Column(name = "profilType")
    private String profilType;

    @Column(name = "quotaPret")
    private Integer quotaPret;

    @OneToMany(mappedBy = "profil")
    private List<AdherentEntity> adherents;

    public ProfilEntity() {}

    public Integer getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(Integer idProfil) {
        this.idProfil = idProfil;
    }

    public String getProfilType() {
        return profilType;
    }

    public void setProfilType(String profilType) {
        this.profilType = profilType;
    }

    public Integer getQuotaPret() {
        return quotaPret;
    }

    public void setQuotaPret(Integer quotaPret) {
        this.quotaPret = quotaPret;
    }

    public List<AdherentEntity> getAdherents() {
        return adherents;
    }

    public void setAdherents(List<AdherentEntity> adherents) {
        this.adherents = adherents;
    }
}