package org.polytech.covidapi.controller;

import lombok.NonNull;
import org.polytech.covidapi.controller.body.ReadPatient;
import org.polytech.covidapi.model.Patient;
import org.polytech.covidapi.model.Reservation;
import org.polytech.covidapi.service.MedecinService;
import org.polytech.covidapi.service.PatientService;
import org.polytech.covidapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/medecin/")
public class MedecinController {
	private final MedecinService medecins;
	private final PatientService patients;
	private final ReservationService reservations;

	@Autowired
	public MedecinController(MedecinService medecins, PatientService patients, ReservationService reservations) {
		this.medecins = medecins;
		this.patients = patients;
		this.reservations = reservations;
	}

	//// Patient
	@GetMapping("/patient/")
	public List<Patient> getPatients(@RequestBody ReadPatient body) {
		return patients.getAll(body.getNom(), body.getPrenom());
	}

	//// Vaccination
	@PutMapping("/patient/{id}/")
	public ResponseEntity<Reservation> updatePatient(@NonNull Authentication auth,
													 @PathVariable @NonNull Long id) {
		return ResponseEntity.of(medecins.get(auth.getName())
				.flatMap(medecin -> reservations.get(id)
						.filter(reservation -> medecins.canAlter(medecin, reservation.getCentre()))
						.map(reservations::validate)));
	}
}
