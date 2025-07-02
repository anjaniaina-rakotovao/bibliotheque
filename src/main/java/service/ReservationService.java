package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import entities.ReservationEntity;
import repository.ReservationRepository;

public class ReservationService {

    private ReservationRepository reservationRepository;

    @Autowired
    public void setReservationRepository(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationEntity> findAll() {
        return reservationRepository.findAll();
    }

    public ReservationEntity save(ReservationEntity reservation) {
        return reservationRepository.save(reservation);
    }

    public void delete(Integer id) {
        reservationRepository.deleteById(id);
    }
}