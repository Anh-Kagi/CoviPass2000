package org.polytech.covidapi.controller;

import lombok.NonNull;
import org.polytech.covidapi.model.Account;
import org.polytech.covidapi.model.Reservation;
import org.polytech.covidapi.service.CentreService;
import org.polytech.covidapi.service.MedecinService;
import org.polytech.covidapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/simple/")
public class AdminController {
    private final CentreService centres;
    private final MedecinService medecins;
    private final ReservationService reservations;

    @Autowired
    public AdminController(CentreService centres, MedecinService medecins, ReservationService reservations) {
        this.centres = centres;
        this.medecins = medecins;
        this.reservations = reservations;
    }

    //// Médecins
    @PostMapping("/medecin/")
    public ResponseEntity<Account> createMedecin(@RequestParam @NonNull String username,
                                                 @RequestParam @NonNull String password,
                                                 @RequestParam @NonNull Long centreId) {

        return ResponseEntity.of(centres.get(centreId).map(centre -> medecins.create(username, password, centre)));
    }

    @GetMapping("/medecin/{id}/")
    public ResponseEntity<Account> readMedecin(@PathVariable @NonNull Long id) {
        return ResponseEntity.of(medecins.get(id));
    }

    @PutMapping("/medecin/{id}/")
    public ResponseEntity<Account> updateMedecin(@PathVariable @NonNull Long id,
                                                 @RequestParam(required = false) String username,
                                                 @RequestParam(required = false) String password,
                                                 @RequestParam(required = false) Long centreId) {
        if (centreId == null)
            return ResponseEntity.of(medecins.update(id, username, password, null));
        return ResponseEntity.of(centres.get(centreId).flatMap(centre -> medecins.update(id, username, password, centre)));
    }

    //// Reservations
    @GetMapping("/reservation/{id}/")
    public ResponseEntity<Reservation> readReservation(@PathVariable Long id) {
        return ResponseEntity.of(reservations.get(id));
    }

    @DeleteMapping("/reservation/{id}/")
    public boolean deleteReservation(@PathVariable Long id) {
        return reservations.delete(id);
    }
}
