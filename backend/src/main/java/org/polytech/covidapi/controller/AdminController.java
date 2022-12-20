package org.polytech.covidapi.controller;

import lombok.NonNull;
import org.polytech.covidapi.model.Account;
import org.polytech.covidapi.model.Reservation;
import org.polytech.covidapi.service.AdminService;
import org.polytech.covidapi.service.CentreService;
import org.polytech.covidapi.service.MedecinService;
import org.polytech.covidapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin/simple/")
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
												 @RequestParam @NonNull String username,
												 @RequestParam @NonNull String password,
												 @RequestParam @NonNull Long centreId) {
		return ResponseEntity.of(admins.get(auth.getName())
				.flatMap(admin -> centres.get(centreId)
						.filter(centre -> admins.canAlter(admin, centre))
						.map(centre -> medecins.create(username, password, centre))));
	}

	@GetMapping("/medecin/{id}/")
	public ResponseEntity<Account> readMedecin(@PathVariable @NonNull Long id) {
		return ResponseEntity.of(medecins.get(id));
	}

	@PutMapping("/medecin/{id}/")
	public ResponseEntity<Account> updateMedecin(@NonNull Authentication auth,
												 @PathVariable @NonNull Long id,
												 @RequestParam(required = false) String username,
												 @RequestParam(required = false) String password,
												 @RequestParam(required = false) Long centreId) {
		return ResponseEntity.of(admins.get(auth.getName())
				.flatMap(admin -> medecins.get(id)
						.filter(medecin -> admins.canAlter(admin, medecin.getCentre()))
						.flatMap(medecin -> centreId == null ?
								Optional.of(medecins.update(medecin, username, password, null)) :
								centres.get(centreId).map(centre -> medecins.update(medecin, username, password, centre))
						)));
	}

	//// Reservations
	@GetMapping("/reservation/{id}/")
	public ResponseEntity<Reservation> readReservation(@PathVariable @NonNull Long id) {
		return ResponseEntity.of(reservations.get(id));
	}

	@DeleteMapping("/reservation/{id}/")
	public ResponseEntity<Reservation> deleteReservation(@NonNull Authentication auth,
														 @PathVariable @NonNull Long id) {
		return ResponseEntity.of(admins.get(auth.getName())
				.flatMap(admin -> reservations.get(id)
						.filter(reservation -> admins.canAlter(admin, reservation.getCentre()))
						.filter(reservation -> {
							reservations.delete(reservation);
							return true;
						})));
	}
}
