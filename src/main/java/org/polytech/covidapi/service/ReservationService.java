package org.polytech.covidapi.service;

import org.polytech.covidapi.model.Patient;
import org.polytech.covidapi.model.Reservation;
import org.polytech.covidapi.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private final ReservationRepository reservations;

    @Autowired
    public ReservationService(ReservationRepository reservations) {
        this.reservations = reservations;
    }

    public Reservation get(Long id) {
        return reservations.findReservationById(id);
    }

    public boolean delete(Long id) {
        if (reservations.existsById(id)) {
            reservations.deleteById(id);
            return true;
        }
        return false;
    }

    public Patient validate(Long id) {
        Reservation reservation = get(id);
        if (reservation != null) {
            reservation.setFaite(true);
        }
        return reservation.getPatient();
    }
}
