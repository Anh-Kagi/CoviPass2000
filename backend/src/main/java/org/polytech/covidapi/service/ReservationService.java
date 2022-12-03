package org.polytech.covidapi.service;

import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.model.Patient;
import org.polytech.covidapi.model.Reservation;
import org.polytech.covidapi.repository.CentreRepository;
import org.polytech.covidapi.repository.PatientRepository;
import org.polytech.covidapi.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private final ReservationRepository reservations;
    private final PatientRepository patients;
    private final CentreRepository centres;

    @Autowired
    public ReservationService(ReservationRepository reservations, PatientRepository patients, CentreRepository centres) {
        this.reservations = reservations;
        this.patients = patients;
        this.centres = centres;
    }

    public Reservation get(Long id) {
        return reservations.findReservationById(id);
    }

    public Reservation create(Long centreId, String nom, String prenom, String mail, long telephone) {
        Centre centre = centres.findCentreById(centreId);
        if (centre == null)
            return null;

        Patient patient = patients.findPatient(nom, prenom, mail, telephone);
        if (patient == null)
            patient = new Patient(nom, prenom, mail, telephone);

        Reservation reservation = new Reservation(centre, patient);
        return reservations.save(reservation);
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
