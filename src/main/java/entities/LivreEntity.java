package entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "livre")
public class LivreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLivre")
    private Integer idLivre;

    @Column(name = "isbn", unique = true)
    private String isbn;

    @Column(name = "editionDate")
    private LocalDate editionDate;

    @Column(name = "auteur")
    private String auteur;
    
    @Column(name = "titre")
    private String titre;

    @ManyToOne
    @JoinColumn(name = "idCategorieAge")
    private CategorieAgeEntity categorieAge;  // Changé de String à CategorieAgeEntity

    @OneToMany(mappedBy = "livre")
    private List<TagLivreEntity> tags;

    @OneToMany(mappedBy = "livre")
    private List<ExemplaireEntity> exemplaires;

    @OneToMany(mappedBy = "livre")
    private List<LivreProfilEntity> livreProfils;

    public LivreEntity() {
    }

    // Getters et setters
    public Integer getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(Integer idLivre) {
        this.idLivre = idLivre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getEditionDate() {
        return editionDate;
    }

    public void setEditionDate(LocalDate editionDate) {
        this.editionDate = editionDate;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public CategorieAgeEntity getCategorieAge() {
        return categorieAge;
    }

    public void setCategorieAge(CategorieAgeEntity categorieAge) {
        this.categorieAge = categorieAge;
    }

    public List<TagLivreEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagLivreEntity> tags) {
        this.tags = tags;
    }

    public List<ExemplaireEntity> getExemplaires() {
        return exemplaires;
    }

    public void setExemplaires(List<ExemplaireEntity> exemplaires) {
        this.exemplaires = exemplaires;
    }

    public List<LivreProfilEntity> getLivreProfils() {
        return livreProfils;
    }

    public void setLivreProfils(List<LivreProfilEntity> livreProfils) {
        this.livreProfils = livreProfils;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
