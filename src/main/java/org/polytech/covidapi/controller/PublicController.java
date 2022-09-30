package org.polytech.covidapi.controller;

import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.model.Inscription;
import org.polytech.covidapi.repository.CentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/public/")
public class PublicController {
    @GetMapping("/centre/")
    public List<Centre> rechercherCentre(CentreRepository centres, @Autowired @RequestParam(required=true) String ville) {
        // fetch centres

        return centres.findAllByVille(ville);

    }

    @PostMapping("/inscrire/")
    public Inscription inscrire() {
        return null;
    }
}
