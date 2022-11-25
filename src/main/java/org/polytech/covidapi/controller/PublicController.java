package org.polytech.covidapi.controller;

import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.model.Reservation;
import org.polytech.covidapi.service.CentreService;
import org.polytech.covidapi.service.RateLimitService;
import org.polytech.covidapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/public/")
public class PublicController {
    private final CentreService centres;
    private final ReservationService reservations;
    private final RateLimitService rateLimit;

    @Autowired
    public PublicController(CentreService centres, ReservationService reservations, RateLimitService rateLimit) {
        this.centres = centres;
        this.reservations = reservations;
        this.rateLimit = rateLimit;
    }

    @GetMapping("/centre/")
    public ResponseEntity<List<Centre>> rechercherCentre(@RequestParam String ville) {
        Optional<ResponseEntity> token = rateLimit.tryConsume();
        if (token.isPresent()) {
            return token.get();
        }
        return ResponseEntity.ok(centres.getAllByVille(ville));
    }

    @PostMapping("/inscrire/")
    public ResponseEntity<Reservation> inscrire(@RequestParam Long centreId, String nom, String prenom, String mail, long telephone) {
        Optional<ResponseEntity> token = rateLimit.tryConsume();
        if (token.isPresent()) {
            return token.get();
        }
        return ResponseEntity.ok(reservations.create(centreId, nom, prenom, mail, telephone));
    }
}
