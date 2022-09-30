package org.polytech.covidapi.controller;

import org.polytech.covidapi.model.Medecin;
import org.polytech.covidapi.model.Reservation;
import org.polytech.covidapi.service.MedecinService;
import org.polytech.covidapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/simple/")
public class AdminController {
    private final MedecinService medecins;
    private final ReservationService reservations;

    @Autowired
    public AdminController(MedecinService medecins, ReservationService reservations) {
        this.medecins = medecins;
        this.reservations = reservations;
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
    public Reservation readReservation(@PathVariable Long id) {
        return reservations.get(id);
    }

    @DeleteMapping("/reservation/{id}/")
    public boolean deleteReservation(@PathVariable Long id) {
        return reservations.delete(id);
    }
}
