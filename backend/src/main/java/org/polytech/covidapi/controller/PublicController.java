package org.polytech.covidapi.controller;

import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.model.Inscription;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/public/")
public class PublicController {
    @GetMapping("/centre/")
    public List<Centre> listCentre() {
        // fetch centres
        return new LinkedList<Centre>();
    }

    @PostMapping("/inscrire/")
    public Inscription inscrire() {
        return null;
    }
}
