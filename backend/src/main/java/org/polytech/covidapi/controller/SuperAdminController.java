package org.polytech.covidapi.controller;

import lombok.NonNull;
import org.polytech.covidapi.model.Account;
import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.service.AdminService;
import org.polytech.covidapi.service.CentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin/super/")
public class SuperAdminController {
    private final CentreService centres;
    private final AdminService admins;

    @Autowired
    public SuperAdminController(CentreService centres, AdminService admins) {
        this.centres = centres;
        this.admins = admins;
    }

    //// Centres
    @PostMapping("/centre/")
    public Centre createCentre(@RequestParam String nom, @RequestParam String ville) {
        return centres.create(nom, ville);
    }

    @GetMapping("/centre/{id}/")
    public Optional<Centre> readCentre(@PathVariable Long id) {
        return centres.get(id);
    }

    @PutMapping("/centre/{id}/")
    public Optional<Centre> updateCentre(@PathVariable Long id, @RequestParam(required = false) String nom, @RequestParam(required = false) String ville) {
        return centres.update(id, nom, ville);
    }

    @DeleteMapping("/centre/{id}/")
    public boolean deleteCentre(@PathVariable Long id) {
        return centres.delete(id);
    }

    //// Admins
    @PostMapping("/admin/")
    public Optional<Account> createAdmin(@RequestParam @NonNull String username, @RequestParam @NonNull String password, @RequestParam @NonNull Long centreId) {
        return admins.create(username, password, centreId);
    }

    @GetMapping("/admin/{id}/")
    public Optional<Account> readAdmin(@PathVariable Long id) {
        return admins.get(id);
    }

    @PutMapping("/admin/{id}/")
    public Optional<Account> updateAdmin(@PathVariable Long id, @RequestParam(required = false) String username, @RequestParam(required = false) String password, @RequestParam(required = false) Long centreId) {
        return admins.update(id, username, password, centreId);
    }

    @DeleteMapping("/admin/{id}/")
    public boolean deleteAdmin(@PathVariable Long id) {
        return admins.delete(id);
    }
}
