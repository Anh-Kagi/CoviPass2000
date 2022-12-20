package org.polytech.covidapi.controller;

import lombok.NonNull;
import org.polytech.covidapi.model.Account;
import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.service.AdminService;
import org.polytech.covidapi.service.CentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin/super/")
public class SuperAdminController {
	private final CentreService centres;
	private final AdminService admins;

	@Autowired
	public SuperAdminController(CentreService centres, AdminService admins) {
		this.centres = centres;
		this.admins = admins;
	}

	//// Centres
	@PostMapping("/centre/")
	public Centre createCentre(@RequestParam @NonNull String nom,
							   @RequestParam @NonNull String ville) {
		return centres.create(nom, ville);
	}

	@GetMapping("/centre/{id}/")
	public Optional<Centre> readCentre(@PathVariable @NonNull Long id) {
		return centres.get(id);
	}

	@PutMapping("/centre/{id}/")
	public ResponseEntity<Centre> updateCentre(@PathVariable @NonNull Long id,
											   @RequestParam(required = false) String nom,
											   @RequestParam(required = false) String ville) {
		return ResponseEntity.of(centres.get(id).map(centre -> centres.update(centre, nom, ville)));
	}

	@DeleteMapping("/centre/{id}/")
	public ResponseEntity<Centre> deleteCentre(@PathVariable @NonNull Long id) {
		return ResponseEntity.of(centres.get(id)
				.filter(centre -> {
					centres.delete(centre);
					return true;
				}));
	}

	//// Admins
	@PostMapping("/admin/")
	public Optional<Account> createAdmin(@RequestParam @NonNull String username,
										 @RequestParam @NonNull String password,
										 @RequestParam @NonNull Long centreId) {
		return centres.get(centreId).map(centre -> admins.create(username, password, centre));
	}

	@GetMapping("/admin/{id}/")
	public Optional<Account> readAdmin(@PathVariable Long id) {
		return admins.get(id);
	}

	@PutMapping("/admin/{id}/")
	public ResponseEntity<Account> updateAdmin(@PathVariable @NonNull Long id,
											   @RequestParam(required = false) String username,
											   @RequestParam(required = false) String password,
											   @RequestParam(required = false) Long centreId) {
		return ResponseEntity.of(admins.get(id).flatMap(admin -> centreId == null ?
				Optional.of(admins.update(admin, username, password, null)) :
				centres.get(centreId).map(centre -> admins.update(admin, username, password, centre))));
	}

	@DeleteMapping("/admin/{id}/")
	public ResponseEntity<Account> deleteAdmin(@PathVariable @NonNull Long id) {
		return ResponseEntity.of(admins.get(id)
				.filter(admin -> {
					admins.delete(admin);
					return true;
				}));
	}
}
