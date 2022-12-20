package org.polytech.covidapi.repository;

import org.polytech.covidapi.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
