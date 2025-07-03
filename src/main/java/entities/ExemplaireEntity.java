package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "exemplaire")
public class ExemplaireEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idExemplaire")
    private Integer idExemplaire;

    @ManyToOne
    @JoinColumn(name = "idLivre")
    private LivreEntity livre;

    @Column(name = "nbExemplaire")
    private Integer nbExemplaire;


    public ExemplaireEntity() {}


    public Integer getIdExemplaire() {
        return idExemplaire;
    }


    public void setIdExemplaire(Integer idExemplaire) {
        this.idExemplaire = idExemplaire;
    }


    public LivreEntity getLivre() {
        return livre;
    }


    public void setLivre(LivreEntity livre) {
        this.livre = livre;
    }


    public Integer getNbExemplaire() {
        return nbExemplaire;
    }


    public void setNbExemplaire(Integer nbExemplaire) {
        this.nbExemplaire = nbExemplaire;
    }
}
