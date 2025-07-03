package entities;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tagLivre")
public class TagLivreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTagLivre")
    private Integer idTagLivre;

    @ManyToOne
    @JoinColumn(name = "idLivre")
    private LivreEntity livre;

    @ManyToOne
    @JoinColumn(name = "idTag")
    private TagEntity tag;

    public TagLivreEntity() {}

    public Integer getIdTagLivre() {
        return idTagLivre;
    }

    public void setIdTagLivre(Integer idTagLivre) {
        this.idTagLivre = idTagLivre;
    }

    public LivreEntity getLivre() {
        return livre;
    }

    public void setLivre(LivreEntity livre) {
        this.livre = livre;
    }

    public TagEntity getTag() {
        return tag;
    }

    public void setTag(TagEntity tag) {
        this.tag = tag;
    }
}
