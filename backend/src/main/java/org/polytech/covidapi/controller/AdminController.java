package org.polytech.covidapi.controller;

import org.polytech.covidapi.model.Inscription;
import org.polytech.covidapi.model.Medecin;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/simple/")
public class AdminController {
    //// Médecins
    @PostMapping("/medecin/")
    public Medecin createMedecin() {
        return null;
    }

    @GetMapping("/medecin/{id}/")
    public Medecin readMedecin(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/medecin/{id}/")
    public Medecin updateMedecin(@PathVariable Long id) {
        return null;
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
