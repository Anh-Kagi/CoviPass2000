package org.polytech.covidapi.controller;

import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.model.Reservation;
import org.polytech.covidapi.service.CentreService;
import org.polytech.covidapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/")
public class PublicController {
    private final CentreService centres;
    private final ReservationService reservations;

    @Autowired
    public PublicController(CentreService centres, ReservationService reservations) {
        this.centres = centres;
        this.reservations = reservations;
    }

    @GetMapping("/centre/")
    public List<Centre> rechercherCentre(@RequestParam(required = true) String ville) {
        return centres.getAllByVille(ville);

    }

    @PostMapping("/inscrire/")
    public Reservation inscrire(@RequestParam Long centreId, String nom, String prenom, String mail, long telephone) {
        return reservations.create(centreId, nom, prenom, mail, telephone);
    }
}
