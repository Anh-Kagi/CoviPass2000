package org.polytech.covidapi.controller;

import io.micrometer.core.instrument.Metrics;
import lombok.NonNull;
import org.polytech.covidapi.controller.body.ReadPatient;
import org.polytech.covidapi.model.Account;
import org.polytech.covidapi.model.Patient;
import org.polytech.covidapi.model.Reservation;
import org.polytech.covidapi.service.MedecinService;
import org.polytech.covidapi.service.PatientService;
import org.polytech.covidapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

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
	public ResponseEntity<List<Patient>> getPatients(@RequestBody ReadPatient body) {
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(Duration.ofHours(1)))
				.body(patients.getAll(body.getNom(), body.getPrenom()));
	}

	//// Vaccination
	@PatchMapping("/patient/{id}/")
	public ResponseEntity<Reservation> updatePatient(@NonNull Authentication auth,
													 @PathVariable @NonNull Long id) {
		Optional<Account> acc_opt = medecins.get(auth.getName());
		if (acc_opt.isEmpty())
			return ResponseEntity.internalServerError().build();
		Account acc = acc_opt.get();

		Optional<Reservation> reservation_opt = reservations.get(id);
		if (reservation_opt.isEmpty())
			return ResponseEntity.notFound().build();
		Reservation reservation = reservation_opt.get();

		if (!medecins.canAlter(acc, reservation.getCentre()))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

		// TODO: multiple vaccination
		reservation = reservations.validate(reservation);
		Metrics.counter("reservations.pending").increment(-1);
		Metrics.counter("reservations.done").increment();
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(Duration.ofDays(1)))
				.body(reservation);
	}
}
