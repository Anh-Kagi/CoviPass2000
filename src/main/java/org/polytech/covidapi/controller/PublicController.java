package org.polytech.covidapi.controller;

import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.model.Reservation;
import org.polytech.covidapi.service.CentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/")
public class PublicController {
    private final CentreService centres;

    @Autowired
    public PublicController(CentreService centres) {
        this.centres = centres;
    }

    @GetMapping("/centre/")
    public List<Centre> rechercherCentre(@RequestParam(required = true) String ville) {
        return centres.getAllByVille(ville);

    }

    @PostMapping("/inscrire/")
    public Reservation inscrire() {
        return null;
    }
}
