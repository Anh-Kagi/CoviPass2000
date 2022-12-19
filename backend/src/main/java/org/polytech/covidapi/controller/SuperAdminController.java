package org.polytech.covidapi.controller;

import org.polytech.covidapi.model.Admin;
import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.service.AdminService;
import org.polytech.covidapi.service.CentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@Secured({ "ROLE_SUPER_ADMIN" })
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
    public Centre readCentre(@PathVariable Long id) {
        return centres.get(id);
    }

    @PutMapping("/centre/{id}/")
    public Centre updateCentre(@PathVariable Long id, @RequestParam(required = false) String nom, @RequestParam(required = false) String ville) {
        return centres.update(id, nom, ville);
    }

    @DeleteMapping("/centre/{id}/")
    public boolean deleteCentre(@PathVariable Long id) {
        return centres.delete(id);
    }

    //// Admins
    @PostMapping("/admin/")
    public Admin createAdmin(@RequestParam Long id) {
        return admins.create(id);
    }

    @GetMapping("/admin/{id}/")
    public Admin readAdmin(@PathVariable Long id) {
        return admins.get(id);
    }

    @PutMapping("/admin/{id}/")
    public Admin updateAdmin(@PathVariable Long id, @RequestParam(required = false) Long centreId) {
        return admins.update(id, centreId);
    }

    @DeleteMapping("/admin/{id}/")
    public boolean deleteAdmin(@PathVariable Long id) {
        return admins.delete(id);
    }
}
