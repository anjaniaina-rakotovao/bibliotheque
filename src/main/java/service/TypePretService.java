package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import entities.TypePretEntity;
import repository.TypePretRepository;

public class TypePretService {

    private TypePretRepository typePretRepository;

    @Autowired
    public void setTypePretRepository(TypePretRepository typePretRepository) {
        this.typePretRepository = typePretRepository;
    }

    public List<TypePretEntity> findAll() {
        return typePretRepository.findAll();
    }

    public TypePretEntity save(TypePretEntity typePret) {
        return typePretRepository.save(typePret);
    }

    public void delete(Integer id) {
        typePretRepository.deleteById(id);
    }

    public TypePretEntity findById(Integer id) {
        return typePretRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type de prêt introuvable"));
    }
    // public TypePretEntity findByType(String typePret) {
    //     return typePretRepository.findByTypePret(typePret);
    // }
}
