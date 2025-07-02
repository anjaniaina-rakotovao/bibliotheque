package entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorieAge")
public class CategorieAgeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategorieAge")
    private Integer idCategorieAge;

    @Column(name = "ageMin")
    private Integer ageMin;

    @Column(name = "ageMax")
    private Integer ageMax;

    @OneToMany(mappedBy = "categorieAge")
    private List<LivreEntity> livres;

    public CategorieAgeEntity() {}

    // Getters et setters
    public Integer getIdCategorieAge() {
        return idCategorieAge;
    }

    public void setIdCategorieAge(Integer idCategorieAge) {
        this.idCategorieAge = idCategorieAge;
    }

    public Integer getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }

    public Integer getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(Integer ageMax) {
        this.ageMax = ageMax;
    }

    public List<LivreEntity> getLivres() {
        return livres;
    }

    public void setLivres(List<LivreEntity> livres) {
        this.livres = livres;
    }
}