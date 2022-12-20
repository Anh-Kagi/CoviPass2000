package org.polytech.covidapi.controller;

import lombok.NonNull;
import org.polytech.covidapi.model.Patient;
import org.polytech.covidapi.model.Reservation;
import org.polytech.covidapi.service.PatientService;
import org.polytech.covidapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/medecin/")
public class MedecinController {
    private final PatientService patients;
    private final ReservationService reservations;

    @Autowired
    public MedecinController(PatientService patients, ReservationService reservations) {
        this.patients = patients;
        this.reservations = reservations;
    }

    //// Patient
    @GetMapping("/patient/")
    public List<Patient> getPatients(@RequestParam(required = false) String nom, @RequestParam(required = false) String prenom) {
        return patients.getAll(nom, prenom);
    }

    //// Vaccination
    @PutMapping("/patient/{id}/")
    public ResponseEntity<Reservation> updatePatient(@PathVariable @NonNull Long id) {
        return ResponseEntity.of(reservations.validate(id));
    }
}
