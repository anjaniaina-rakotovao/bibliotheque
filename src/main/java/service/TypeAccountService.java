package service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import entities.TypeAccountEntity;
import repository.TypeAccountRepository;

@Service
public class TypeAccountService {

    @Autowired
    private TypeAccountRepository typeAccountRepository;

    // CREATE
    public TypeAccountEntity createTypeAccount(String accountType) {
        TypeAccountEntity newType = new TypeAccountEntity();
        newType.setAccountType(accountType);
        return typeAccountRepository.save(newType);
    }

    // READ
    public List<TypeAccountEntity> getAllTypeAccounts() {
        return typeAccountRepository.findAll();
    }

    public TypeAccountEntity getTypeAccountById(Integer id) {
    return typeAccountRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Type de compte non trouvé avec l'id: " + id));
}

    public Optional<TypeAccountEntity> getTypeAccountByName(String accountType) {
        return typeAccountRepository.findByAccountType(accountType);
    }

    // UPDATE
    public TypeAccountEntity updateTypeAccount(Integer id, String newAccountType) {
        TypeAccountEntity typeAccount = typeAccountRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Type de compte non trouvé"));
        typeAccount.setAccountType(newAccountType);
        return typeAccountRepository.save(typeAccount);
    }

    // DELETE
    public void deleteTypeAccount(Integer id) {
        typeAccountRepository.deleteById(id);
    }

    // UTILITY
    public boolean existsByAccountType(String accountType) {
        return typeAccountRepository.existsByAccountType(accountType);
    }
}