package org.polytech.covidapi.service;

import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.model.Medecin;
import org.polytech.covidapi.repository.CentreRepository;
import org.polytech.covidapi.repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedecinService {
    private final MedecinRepository medecins;
    private final CentreRepository centres;

    @Autowired
    public MedecinService(MedecinRepository medecins, CentreRepository centres) {
        this.medecins = medecins;
        this.centres = centres;
    }

    public Medecin create(Long centreId) {
        Centre centre = centres.findCentreById(centreId);
        Medecin medecin = new Medecin(centre);
        return medecins.save(medecin);
    }

    public Medecin get(Long id) {
        return medecins.findMedecinById(id);
    }

    public Medecin update(Long id, Long centreId) {
        Centre centre = centres.findCentreById(id);
        Medecin medecin = get(id);
        medecin.setCentre(centre);
        return medecins.save(medecin);
    }
}
