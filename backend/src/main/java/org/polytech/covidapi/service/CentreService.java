package org.polytech.covidapi.service;

import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.repository.CentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Centre> get(Long id) {
        return centres.findCentreById(id);
    }

    public List<Centre> getAllByVille(String ville) {
        return centres.findAllByVille(ville);
    }

    public Optional<Centre> update(Long id, String nom, String ville) {
        Optional<Centre> centre_opt = get(id);
        if (centre_opt.isPresent()) {
            Centre centre = centre_opt.get();
            if (nom != null)
                centre.setNom(nom);
            if (ville != null)
                centre.setVille(ville);
            return Optional.of(centres.save(centre));
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        if (centres.existsById(id)) {
            centres.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
