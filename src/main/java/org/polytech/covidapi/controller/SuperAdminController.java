package org.polytech.covidapi.controller;

import org.polytech.covidapi.model.Admin;
import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.repository.CentreRepository;
import org.polytech.covidapi.service.CentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/super/")
public class SuperAdminController {
    private final CentreService centres;

    @Autowired
    public SuperAdminController(CentreService centres) {
        this.centres = centres;
    }

    //// Centres
    @PostMapping("/centre/")
    public Centre createCentre(@RequestParam(required=true) String nom, @RequestParam(required=true) String ville) {
        return centres.create(nom, ville);
    }

    @GetMapping("/centre/{id}/")
    public Centre readCentre(@PathVariable Long id) {
        return centres.get(id);
    }

    @PutMapping("/centre/{id}/")
    public Centre updateCentre(@PathVariable Long id, @RequestParam String nom, @RequestParam String ville) {
        return centres.update(id, nom, ville);
    }

    @DeleteMapping("/centre/{id}/")
    public boolean deleteCentre(@PathVariable Long id) {
        return centres.delete(id);
    }

    //// Admins
    @PostMapping("/admin/")
    public Admin createAdmin() {
        return null;
    }

    @GetMapping("/admin/{id}/")
    public Admin readAdmin(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/admin/{id}/")
    public Admin updateAdmin(@PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/admin/{id}/")
    public boolean deleteAdmin(@PathVariable Long id) {
        return false;
    }
}
