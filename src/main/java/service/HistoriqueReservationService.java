package service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import entities.HistoriqueReservationEntity;
import entities.ReservationEntity;
import repository.HistoriqueReservationRepository;

public class HistoriqueReservationService {

    @Autowired
    private HistoriqueReservationRepository historiqueReservationRepository;

    @Autowired
    public void setHistoriqueReservationRepository(HistoriqueReservationRepository historiqueReservationRepository) {
        this.historiqueReservationRepository = historiqueReservationRepository;
    }

    public List<HistoriqueReservationEntity> findAll() {
        return historiqueReservationRepository.findAll();
    }

    public HistoriqueReservationEntity save(HistoriqueReservationEntity historique) {
        return historiqueReservationRepository.save(historique);
    }

    public void delete(Integer id) {
        historiqueReservationRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<HistoriqueReservationEntity> getDerniersReservationsEnAttenteParReservation() {
        List<HistoriqueReservationEntity> tous = historiqueReservationRepository.findAll();
        Map<ReservationEntity, Optional<HistoriqueReservationEntity>> derniersParReservation = tous.stream()
                .collect(Collectors.groupingBy(
                        HistoriqueReservationEntity::getReservation,
                        Collectors.maxBy(Comparator.comparing(HistoriqueReservationEntity::getDateMouvement))
                ));

        return derniersParReservation.values().stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(p -> p.getStatut().equals("attente"))
                .toList();
    }

}
