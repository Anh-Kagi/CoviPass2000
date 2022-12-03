package org.polytech.covidapi.service;

import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.repository.CentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentreService {
    private final CentreRepository centres;

    @Autowired
    public CentreService(CentreRepository centres) {
        this.centres = centres;
    }

    public Centre create(String nom, String ville) {
        Centre centre = new Centre(nom, ville);
        return centres.save(centre);
    }

    public Centre get(Long id) {
        return centres.findCentreById(id);
    }

    public List<Centre> getAllByVille(String ville) {
        return centres.findAllByVille(ville);
    }

    public Centre update(Long id, String nom, String ville) {
        Centre centre = get(id);
        if (nom != null)
            centre.setNom(nom);
        if (ville != null)
            centre.setVille(ville);
        return save(centre);
    }

    public boolean delete(Long id) {
        if (centres.existsById(id)) {
            centres.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private Centre save(Centre centre) {
        return centres.save(centre);
    }
}
