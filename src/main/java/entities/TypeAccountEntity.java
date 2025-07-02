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
@Table(name = "typeAccount")
public class TypeAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTypeAccount")
    private Integer idTypeAccount;

    @Column(name = "accountType", nullable = false, unique=true)
    private String accountType;

    public TypeAccountEntity(){

    }

    public Integer getIdTypeAccount() {
        return idTypeAccount;
    }

    public void setIdTypeAccount(Integer idTypeAccount) {
        this.idTypeAccount = idTypeAccount;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    
}
