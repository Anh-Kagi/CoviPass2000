package org.polytech.covidapi.controller;

import lombok.NonNull;
import org.polytech.covidapi.controller.body.CreateMedecin;
import org.polytech.covidapi.controller.body.UpdateMedecin;
import org.polytech.covidapi.model.Account;
import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.model.Reservation;
import org.polytech.covidapi.service.AdminService;
import org.polytech.covidapi.service.CentreService;
import org.polytech.covidapi.service.MedecinService;
import org.polytech.covidapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;
import java.util.Optional;

@RestController
@RequestMapping("/private/admin/")
public class AdminController {
	private final AdminService admins;
	private final CentreService centres;
	private final MedecinService medecins;
	private final ReservationService reservations;

	@Autowired
	public AdminController(AdminService admins, CentreService centres, MedecinService medecins, ReservationService reservations) {
		this.admins = admins;
		this.centres = centres;
		this.medecins = medecins;
		this.reservations = reservations;
	}

	//// Médecins
	@PostMapping("/medecin/")
	public ResponseEntity<Account> createMedecin(@NonNull Authentication auth,
												 @RequestBody CreateMedecin body,
												 UriComponentsBuilder builder) {
		Optional<Account> acc_opt = admins.get(auth.getName());
		if (acc_opt.isEmpty())
			return ResponseEntity.internalServerError().build();
		Account acc = acc_opt.get();

		Optional<Centre> centre_opt = centres.get(body.getCentre());
		if (centre_opt.isEmpty())
			return ResponseEntity.badRequest().build();
		Centre centre = centre_opt.get();

		if (!admins.canAlter(acc, centre))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

		Account medecin = medecins.create(body.getUsername(), body.getPassword(), centre);
		return ResponseEntity.created(builder.path("/admin/simple/medecin/{id}/").buildAndExpand(medecin.getId()).toUri()).build();
	}

	@GetMapping("/medecin/{id}/")
	public ResponseEntity<Account> readMedecin(@PathVariable @NonNull Long id) {
		Optional<Account> medecin = medecins.get(id);
		return medecin.map(account -> ResponseEntity.ok()
						.cacheControl(CacheControl.maxAge(Duration.ofMinutes(5)).mustRevalidate())
						.body(account))
				.orElseGet(() -> ResponseEntity.notFound()
						.build());
	}

	@PutMapping("/medecin/{id}/")
	public ResponseEntity<Account> updateMedecin(@NonNull Authentication auth,
												 @PathVariable @NonNull Long id,
												 @RequestBody UpdateMedecin body) {
		Optional<Account> acc_opt = admins.get(auth.getName());
		if (acc_opt.isEmpty())
			return ResponseEntity.internalServerError().build();
		Account acc = acc_opt.get();

		Centre centre;
		if (body.getCentre() != null) {
			Optional<Centre> centre_opt = centres.get(body.getCentre());
			if (centre_opt.isEmpty())
				return ResponseEntity.badRequest().build();
			centre = centre_opt.get();
		} else {
			centre = null;
		}

		Optional<Account> medecin_opt = medecins.get(id);
		if (medecin_opt.isEmpty())
			return ResponseEntity.notFound().build();
		Account medecin = medecin_opt.get();

		if (!admins.canAlter(acc, medecin.getCentre()))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

		medecin = medecins.update(medecin_opt.get(), body.getUsername(), body.getPassword(), centre);
		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(Duration.ofMinutes(5)).mustRevalidate()).body(medecin);
	}

	//// Reservations
	@GetMapping("/reservation/{id}/")
	public ResponseEntity<Reservation> readReservation(@PathVariable @NonNull Long id) {
		Optional<Reservation> reservation = reservations.get(id);
		return reservation.map(value -> ResponseEntity.ok()
						.cacheControl(CacheControl.maxAge(Duration.ofMinutes(5)).mustRevalidate())
						.body(value))
				.orElseGet(() -> ResponseEntity.notFound()
						.build());
	}

	@DeleteMapping("/reservation/{id}/")
	public ResponseEntity<Reservation> deleteReservation(@NonNull Authentication auth,
														 @PathVariable @NonNull Long id) {
		Optional<Account> acc_opt = admins.get(auth.getName());
		if (acc_opt.isEmpty())
			return ResponseEntity.internalServerError().build();
		Account acc = acc_opt.get();

		Optional<Reservation> reservation_opt = reservations.get(id);
		if (reservation_opt.isEmpty())
			return ResponseEntity.notFound().build();
		Reservation reservation = reservation_opt.get();

		if (!admins.canAlter(acc, reservation.getCentre()))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

		reservations.delete(reservation);
		return ResponseEntity.ok(reservation);
	}
}
