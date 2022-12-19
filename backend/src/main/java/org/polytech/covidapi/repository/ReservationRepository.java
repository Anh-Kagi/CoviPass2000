package org.polytech.covidapi.repository;

import org.polytech.covidapi.model.Patient;
import org.polytech.covidapi.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findReservationById(Long id);

    List<Reservation> findAllByPatient(Patient patient);
}
