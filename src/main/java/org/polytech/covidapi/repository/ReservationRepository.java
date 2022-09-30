package org.polytech.covidapi.repository;

import org.polytech.covidapi.model.Patient;
import org.polytech.covidapi.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation findReservationById(Long id);

    List<Reservation> findAllByPatient(Patient patient);
}
