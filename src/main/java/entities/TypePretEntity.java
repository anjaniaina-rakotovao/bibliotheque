package entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "typePret")
public class TypePretEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTypePret")
    private Integer idTypePret;

    @Column(name = "typePret")
    private String typePret;

    @OneToMany(mappedBy = "typePret")
    private List<PretEntity> prets;

    public TypePretEntity() {}

    public Integer getIdTypePret() {
        return idTypePret;
    }

    public void setIdTypePret(Integer idTypePret) {
        this.idTypePret = idTypePret;
    }

    public String getTypePret() {
        return typePret;
    }

    public void setTypePret(String typePret) {
        this.typePret = typePret;
    }

    public List<PretEntity> getPrets() {
        return prets;
    }

    public void setPrets(List<PretEntity> prets) {
        this.prets = prets;
    }
}