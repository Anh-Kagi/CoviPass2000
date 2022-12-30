package org.polytech.covidapi.controller;

import lombok.NonNull;
import org.apache.logging.log4j.util.Strings;
import org.polytech.covidapi.controller.body.CreateMedecin;
import org.polytech.covidapi.controller.body.UpdateMedecin;
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
												 @RequestBody CreateMedecin body) {
		return ResponseEntity.of(admins.get(auth.getName())
				.flatMap(admin -> centres.get(body.getCentre())
						.filter(centre -> admins.canAlter(admin, centre))
						.map(centre -> medecins.create(body.getUsername(), body.getPassword(), centre))));
	}

	@GetMapping("/medecin/{id}/")
	public ResponseEntity<Account> readMedecin(@PathVariable @NonNull Long id) {
		return ResponseEntity.of(medecins.get(id));
	}

	@PutMapping("/medecin/{id}/")
	public ResponseEntity<Account> updateMedecin(@NonNull Authentication auth,
												 @PathVariable @NonNull Long id,
												 @RequestBody UpdateMedecin body) {
		return ResponseEntity.of(admins.get(auth.getName())
				.flatMap(admin -> medecins.get(id)
						.filter(medecin -> admins.canAlter(admin, medecin.getCentre()))
						.flatMap(medecin -> body.getCentre() == null ?
								Optional.of(medecins.update(medecin,
										Strings.isNotEmpty(body.getUsername()) ? body.getUsername() : null,
										Strings.isNotEmpty(body.getPassword()) ? body.getPassword() : null,
										null)) :
								centres.get(body.getCentre()).map(centre -> medecins.update(medecin,
										Strings.isNotEmpty(body.getUsername()) ? body.getUsername() : null,
										Strings.isNotEmpty(body.getPassword()) ? body.getPassword() : null,
										centre))
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
