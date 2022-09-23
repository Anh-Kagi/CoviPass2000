package org.polytech.covidapi.controller;

import org.polytech.covidapi.model.Patient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/medecin/")
public class MedecinController {
    //// Patient
    @GetMapping("/patient/")
    public List<Patient> getPatients() {
        return null;
    }

    //// Vaccination
    @PutMapping("/patient/{id}/")
    public boolean updatePatient(@PathVariable Long id) {
        return false;
    }
}
