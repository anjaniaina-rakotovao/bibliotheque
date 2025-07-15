package service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import entities.HistoriquePretEntity;
import entities.PretEntity;
import entities.ProlongementPretEntity;
import repository.ProlongementPretRepository;

public class ProlongementPretService {

    @Autowired
    private ProlongementPretRepository prolongementPretRepository;

    @Autowired
    public void setProlongementPretRepository(ProlongementPretRepository prolongementPretRepository) {
        this.prolongementPretRepository = prolongementPretRepository;
    }

    public List<ProlongementPretEntity> findAll() {
        return prolongementPretRepository.findAll();
    }

    public ProlongementPretEntity save(ProlongementPretEntity prolongement) {
        return prolongementPretRepository.save(prolongement);
    }

    public void delete(Integer id) {
        prolongementPretRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ProlongementPretEntity> getProllongementEnAttenteByPret(Integer idPret) {
        List<ProlongementPretEntity> tous = prolongementPretRepository.findByPret_IdPret(idPret);

        return tous.stream()
                .max(Comparator.comparing(ProlongementPretEntity::getDateProlongement))
                .filter(p -> p.getStatus().equalsIgnoreCase("attente"))
                .map(List::of)
                .orElse(List.of());
    }

    @Transactional(readOnly = true)
    public List<ProlongementPretEntity> getDerniersProlongementsEnAttenteParPret() {
        List<ProlongementPretEntity> tous = prolongementPretRepository.findAll();
        Map<PretEntity, Optional<ProlongementPretEntity>> derniersParPret = tous.stream()
                .collect(Collectors.groupingBy(
                        ProlongementPretEntity::getPret,
                        Collectors.maxBy(Comparator.comparing(ProlongementPretEntity::getDateProlongement))
                ));

        return derniersParPret.values().stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(p -> p.getStatus().equals("attente"))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ProlongementPretEntity> getDerniersProlongementsConfirmeParPret() {
        List<ProlongementPretEntity> tous = prolongementPretRepository.findAll();
        Map<PretEntity, Optional<ProlongementPretEntity>> derniersParPret = tous.stream()
                .collect(Collectors.groupingBy(
                        ProlongementPretEntity::getPret,
                        Collectors.maxBy(Comparator.comparing(ProlongementPretEntity::getDateProlongement))
                ));

        return derniersParPret.values().stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(p -> p.getStatus().equals("confirme"))
                .toList();
    }

    public void confirmProlongement(Integer idProlongement, LocalDate dateValidation){
        ProlongementPretEntity p = prolongementPretRepository.findById(idProlongement).orElse(null);
        ProlongementPretEntity p2 = new ProlongementPretEntity();
        p2.setDateProlongement(dateValidation);
        p2.setDuree(p.getDuree());
        p2.setPret(p.getPret());
        p2.setStatus("confirm");
        prolongementPretRepository.save(p2);
    }

}
