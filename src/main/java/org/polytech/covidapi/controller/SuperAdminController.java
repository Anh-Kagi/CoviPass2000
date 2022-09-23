package org.polytech.covidapi.controller;

import org.polytech.covidapi.model.Admin;
import org.polytech.covidapi.model.Centre;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/super/")
public class SuperAdminController {
    //// Centres
    @PostMapping("/centre/")
    public Centre createCentre() {
        return null;
    }

    @GetMapping("/centre/{id}/")
    public Centre readCentre(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/centre/{id}/")
    public Centre updateCentre(@PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/centre/{id}/")
    public boolean deleteCentre(@PathVariable Long id) {
        return false;
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
