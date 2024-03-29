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
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/private/sadmin/")
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
	public ResponseEntity<Centre> createCentre(@RequestBody CreateCentre body, UriComponentsBuilder builder) {
		Centre centre = centres.create(body.getNom(), body.getVille(), body.getAdresse());
		return ResponseEntity.created(builder.path("/admin/super/centre/{id}/").buildAndExpand(centre.getId()).toUri()).build();
	}

	@GetMapping("/centre/")
	public ResponseEntity<List<Centre>> listCentre() {
		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(Duration.ofMinutes(5))).body(centres.list());
	}

	@GetMapping("/centre/{id}/")
	public ResponseEntity<Centre> readCentre(@PathVariable @NonNull Long id) {
		Optional<Centre> centre_opt = centres.get(id);
		if (centre_opt.isEmpty())
			return ResponseEntity.notFound().build();
		Centre centre = centre_opt.get();

		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(Duration.ofMinutes(5)).mustRevalidate())
				.body(centre);
	}

	@PutMapping("/centre/{id}/")
	public ResponseEntity<Centre> updateCentre(@PathVariable @NonNull Long id,
											   @RequestBody UpdateCentre body) {
		Optional<Centre> centre_opt = centres.get(id);
		if (centre_opt.isEmpty())
			return ResponseEntity.notFound().build();
		Centre centre = centres.update(centre_opt.get(), body.getNom(), body.getVille(), body.getAdresse());
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(Duration.ofMinutes(5)).mustRevalidate())
				.body(centre);
	}

	@DeleteMapping("/centre/{id}/")
	public ResponseEntity<Centre> deleteCentre(@PathVariable @NonNull Long id) {
		Optional<Centre> centre_opt = centres.get(id);
		if (centre_opt.isEmpty())
			return ResponseEntity.notFound().build();
		Centre centre = centre_opt.get();

		centres.delete(centre);
		return ResponseEntity.ok(centre);
	}

	//// Admins
	@PostMapping("/admin/")
	public ResponseEntity<Account> createAdmin(@RequestBody CreateAdmin body, UriComponentsBuilder builder) {
		Optional<Centre> centre_opt = centres.get(body.getCentre());
		if (centre_opt.isEmpty())
			return ResponseEntity.badRequest().build();

		Account admin = admins.create(body.getUsername(), body.getPassword(), centre_opt.get());
		return ResponseEntity.created(builder.path("/admin/super/admin/{id}/").buildAndExpand(admin.getId()).toUri()).build();
	}

	@GetMapping("/admin/")
	public ResponseEntity<List<Account>> listAdmin() {
		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(Duration.ofMinutes(5))).body(admins.list());
	}

	@GetMapping("/admin/{id}/")
	public ResponseEntity<Account> readAdmin(@PathVariable Long id) {
		Optional<Account> admin_opt = admins.get(id);
		return admin_opt.map(account -> ResponseEntity.ok()
						.cacheControl(CacheControl.maxAge(Duration.ofMinutes(5)).mustRevalidate())
						.body(account))
				.orElseGet(() -> ResponseEntity.notFound()
						.build());
	}

	@PutMapping("/admin/{id}/")
	public ResponseEntity<Account> updateAdmin(@PathVariable @NonNull Long id,
											   @RequestBody UpdateAdmin body) {
		Optional<Account> admin_opt = admins.get(id);
		if (admin_opt.isEmpty())
			return ResponseEntity.notFound().build();

		Account admin;
		if (body.getCentre() == null) {
			admin = admins.update(admin_opt.get(), body.getUsername(), body.getPassword(), null);
		} else {
			Optional<Centre> centre_opt = centres.get(body.getCentre());
			if (centre_opt.isEmpty())
				return ResponseEntity.badRequest().build();

			admin = admins.update(admin_opt.get(), body.getUsername(), body.getPassword(), centre_opt.get());
		}
		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(Duration.ofMinutes(5)).mustRevalidate()).body(admin);
	}

	@DeleteMapping("/admin/{id}/")
	public ResponseEntity<Account> deleteAdmin(@PathVariable @NonNull Long id) {
		Optional<Account> admin_opt = admins.get(id);
		if (admin_opt.isEmpty())
			return ResponseEntity.notFound().build();
		Account admin = admin_opt.get();

		admins.delete(admin);
		return ResponseEntity.ok(admin);
	}
}
