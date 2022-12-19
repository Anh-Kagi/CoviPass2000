package org.polytech.covidapi.controller;

import lombok.NonNull;
import org.polytech.covidapi.model.Account;
import org.polytech.covidapi.model.Reservation;
import org.polytech.covidapi.model.Role;
import org.polytech.covidapi.service.MedecinService;
import org.polytech.covidapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public Optional<Account> createMedecin(@RequestParam @NonNull String username,
                                           @RequestParam @NonNull String password,
                                           @RequestParam(required = false) Long centre) {
        return medecins.create(username, password, centre, Role.MEDECIN);
    }

    @GetMapping("/medecin/{id}/")
    public Optional<Account> readMedecin(@PathVariable Long id) {
        return medecins.get(id);
    }

    @PutMapping("/medecin/{id}/")
    public Optional<Account> updateMedecin(@PathVariable Long id,
                                           @RequestParam(required = false) String username,
                                           @RequestParam(required = false) String password,
                                           @RequestParam(required = false) Long centre) {
        return medecins.update(id, username, password, centre);
    }

    //// Reservations
    @GetMapping("/reservation/{id}/")
    public Optional<Reservation> readReservation(@PathVariable Long id) {
        return reservations.get(id);
    }

    @DeleteMapping("/reservation/{id}/")
    public boolean deleteReservation(@PathVariable Long id) {
        return reservations.delete(id);
    }
}
