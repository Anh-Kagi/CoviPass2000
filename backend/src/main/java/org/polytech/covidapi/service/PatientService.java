package org.polytech.covidapi.service;

import org.polytech.covidapi.model.Patient;
import org.polytech.covidapi.model.Reservation;
import org.polytech.covidapi.repository.PatientRepository;
import org.polytech.covidapi.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patients;
    private final ReservationRepository reservations;

    @Autowired
    public PatientService(PatientRepository patients, ReservationRepository reservations) {
        this.patients = patients;
        this.reservations = reservations;
    }

    public List<Patient> getAll(String nom, String prenom) {
        if (nom != null && prenom != null) {
            return patients.findAllByNomAndPrenom(nom, prenom);
        } else {
            return patients.findAllByNomOrPrenom(nom, prenom);
        }
    }

    public boolean isVaccine(Long id) {
        final int VACCINAL_SCHEME = 3;

        Patient patient = patients.findPatientById(id);
        if (patient != null) {
            List<Reservation> vaccins = reservations.findAllByPatient(patient);
            return vaccins.size() > VACCINAL_SCHEME;
        }
        return false;
    }
}
