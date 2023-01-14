package org.polytech.covidapi.controller;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.polytech.covidapi.controller.body.Inscription;
import org.polytech.covidapi.model.Account;
import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.model.Reservation;
import org.polytech.covidapi.service.AccountService;
import org.polytech.covidapi.service.CentreService;
import org.polytech.covidapi.service.RateLimitService;
import org.polytech.covidapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/public/")
public class PublicController {
	private final CentreService centres;
	private final ReservationService reservations;
	private final RateLimitService rateLimit;
	private final AccountService accounts;

	@Autowired
	public PublicController(CentreService centres,
	                        ReservationService reservations,
	                        RateLimitService rateLimit,
	                        PrometheusMeterRegistry registry,
	                        AccountService accounts) {
		this.centres = centres;
		this.reservations = reservations;
		this.rateLimit = rateLimit;
		this.accounts = accounts;

		Metrics.addRegistry(registry);
	}

	@GetMapping("/centre/")
	public ResponseEntity<List<Centre>> rechercherCentre(@RequestParam String ville) {
		ville = ville.trim().toLowerCase();
		Optional<ResponseEntity<List<Centre>>> token = rateLimit.tryConsume();
		String finalVille = ville;
		return token.orElseGet(() -> ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(Duration.ofMinutes(5)).mustRevalidate())
				.body(centres.getAllByVille(finalVille)));
	}

	@PostMapping("/inscrire/")
	public ResponseEntity<Reservation> inscrire(@RequestBody Inscription body) {
		Optional<ResponseEntity<Reservation>> token = rateLimit.tryConsume();
		return token.orElseGet(() -> {
			Optional<Centre> centre_opt = centres.get(body.getCentre());
			if (centre_opt.isEmpty())
				return ResponseEntity.badRequest().build();
			Centre centre = centre_opt.get();

			// TODO: check inputs
			body.setNom(body.getNom().trim().toUpperCase());
			body.setPrenom(body.getPrenom().trim().toLowerCase());
			body.setMail(body.getMail().trim().toLowerCase());
			body.setTelephone(body.getTelephone().trim().toLowerCase());

			Reservation reservation = reservations.create(centre, body.getNom(), body.getPrenom(), body.getMail(), body.getTelephone());
			Metrics.counter("reservations.pending").increment();
			return ResponseEntity.ok(reservation);
		});
	}

	@GetMapping("/login/")
	public ResponseEntity<Account> login(Authentication authentication) {
		return Optional.ofNullable(authentication)
				.flatMap(auth -> accounts.find(auth.getName()))
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@GetMapping(value = "/login/", params = {"logout"})
	public ResponseEntity<Account> logout() {
		SecurityContextHolder.clearContext();
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}
