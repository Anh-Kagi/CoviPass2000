package org.polytech.covidapi.controller;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.polytech.covidapi.controller.body.Inscription;
import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.model.Reservation;
import org.polytech.covidapi.service.CentreService;
import org.polytech.covidapi.service.RateLimitService;
import org.polytech.covidapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
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

	@Autowired
	public PublicController(CentreService centres, ReservationService reservations, RateLimitService rateLimit, PrometheusMeterRegistry registry) {
		this.centres = centres;
		this.reservations = reservations;
		this.rateLimit = rateLimit;

		Metrics.addRegistry(registry);
	}

	@GetMapping("/centre/")
	public ResponseEntity<List<Centre>> rechercherCentre(@RequestParam String ville) {
		Optional<ResponseEntity<List<Centre>>> token = rateLimit.tryConsume();
		return token.orElseGet(() -> ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(Duration.ofHours(1)))
				.body(centres.getAllByVille(ville)));
	}

	@PostMapping("/inscrire/")
	public ResponseEntity<Reservation> inscrire(@RequestBody Inscription body) {
		Optional<ResponseEntity<Reservation>> token = rateLimit.tryConsume();
		return token.orElseGet(() -> {
			Optional<Centre> centre_opt = centres.get(body.getCentre());
			if (centre_opt.isEmpty())
				return ResponseEntity.badRequest().build();
			Centre centre = centre_opt.get();

			Reservation reservation = reservations.create(centre, body.getNom(), body.getPrenom(), body.getMail(), body.getTelephone());
			Metrics.counter("reservations.pending").increment();
			return ResponseEntity.ok(reservation);
		});
	}
}
