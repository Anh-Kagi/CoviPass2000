package org.polytech.covidapi.service;

import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.model.Patient;
import org.polytech.covidapi.model.Reservation;
import org.polytech.covidapi.repository.CentreRepository;
import org.polytech.covidapi.repository.PatientRepository;
import org.polytech.covidapi.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional<Reservation> get(Long id) {
        return reservations.findReservationById(id);
    }

    public Optional<Reservation> create(Long centreId, String nom, String prenom, String mail, long telephone) {
        Optional<Centre> centre_opt = centres.findCentreById(centreId);
        if (centre_opt.isPresent()) {
            Centre centre = centre_opt.get();
            Optional<Patient> patient_opt = patients.findPatient(nom, prenom, mail, telephone);
            Patient patient;
            if (patient_opt.isPresent()) {
                patient = patient_opt.get();
            } else {
                patient = new Patient(nom, prenom, mail, telephone);
                patients.save(patient);
            }

            Reservation reservation = new Reservation(centre, patient);
            return Optional.of(reservations.save(reservation));
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        if (reservations.existsById(id)) {
            reservations.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Reservation> validate(Long id) {
        Optional<Reservation> reservation_opt = get(id);
        if (reservation_opt.isPresent()) {
            Reservation reservation = reservation_opt.get();
            reservation.setFaite(true);
            return Optional.of(reservation);
        }
        return Optional.empty();
    }
}
