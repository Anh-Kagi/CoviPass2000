package org.polytech.covidapi.controller;

import org.polytech.covidapi.model.Inscription;
import org.polytech.covidapi.model.Medecin;
import org.polytech.covidapi.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/simple/")
public class AdminController {
    private final MedecinService medecins;

    @Autowired
    public AdminController(MedecinService medecins) {
        this.medecins = medecins;
    }

    //// Médecins
    @PostMapping("/medecin/")
    public Medecin createMedecin(@RequestParam(required = false) Long centre) {
        return medecins.create(centre);
    }

    @GetMapping("/medecin/{id}/")
    public Medecin readMedecin(@PathVariable Long id) {
        return medecins.get(id);
    }

    @PutMapping("/medecin/{id}/")
    public Medecin updateMedecin(@PathVariable Long id, @RequestParam(required = false) Long centre) {
        return medecins.update(id, centre);
    }

    //// Réservations
    @GetMapping("/reservation/{id}/")
    public Inscription readReservation(@PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/reservation/{id}/")
    public boolean deleteReservation(@PathVariable Long id) {
        return false;
    }
}
