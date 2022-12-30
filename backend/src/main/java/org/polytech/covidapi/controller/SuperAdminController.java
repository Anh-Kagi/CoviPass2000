package org.polytech.covidapi.controller;

import lombok.NonNull;
import org.polytech.covidapi.controller.body.CreateAdmin;
import org.polytech.covidapi.controller.body.CreateCentre;
import org.polytech.covidapi.controller.body.UpdateAdmin;
import org.polytech.covidapi.controller.body.UpdateCentre;
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
	public Centre createCentre(@RequestBody CreateCentre body) {
		return centres.create(body.getNom(), body.getVille());
	}

	@GetMapping("/centre/{id}/")
	public Optional<Centre> readCentre(@PathVariable @NonNull Long id) {
		return centres.get(id);
	}

	@PutMapping("/centre/{id}/")
	public ResponseEntity<Centre> updateCentre(@PathVariable @NonNull Long id,
											   @RequestBody UpdateCentre body) {
		return ResponseEntity.of(centres.get(id).map(centre -> centres.update(centre, body.getNom(), body.getVille())));
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
	public Optional<Account> createAdmin(@RequestBody CreateAdmin body) {
		return centres.get(body.getCentre()).map(centre -> admins.create(body.getUsername(), body.getPassword(), centre));
	}

	@GetMapping("/admin/{id}/")
	public Optional<Account> readAdmin(@PathVariable Long id) {
		return admins.get(id);
	}

	@PutMapping("/admin/{id}/")
	public ResponseEntity<Account> updateAdmin(@PathVariable @NonNull Long id,
											   @RequestBody UpdateAdmin body) {
		return ResponseEntity.of(admins.get(id).flatMap(admin -> body.getCentre() == null ?
				Optional.of(admins.update(admin, body.getUsername(), body.getPassword(), null)) :
				centres.get(body.getCentre()).map(centre -> admins.update(admin, body.getUsername(), body.getPassword(), centre))));
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
