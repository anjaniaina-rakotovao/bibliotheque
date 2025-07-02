package entities;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "livreProfil")
public class LivreProfilEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLivreProfil")
    private Integer idLivreProfil;

    @ManyToOne
    @JoinColumn(name = "idLivre")
    private LivreEntity livre;

    @ManyToOne
    @JoinColumn(name = "idProfil")
    private ProfilEntity profil;

    public LivreProfilEntity() {}

    public Integer getIdLivreProfil() {
        return idLivreProfil;
    }

    public void setIdLivreProfil(Integer idLivreProfil) {
        this.idLivreProfil = idLivreProfil;
    }

    public LivreEntity getLivre() {
        return livre;
    }

    public void setLivre(LivreEntity livre) {
        this.livre = livre;
    }

    public ProfilEntity getProfil() {
        return profil;
    }

    public void setProfil(ProfilEntity profil) {
        this.profil = profil;
    }
}