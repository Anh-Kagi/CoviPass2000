package org.polytech.covidapi.service;

import lombok.NonNull;
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

    public Centre create(@NonNull String nom, @NonNull String ville) {
        return centres.save(new Centre(nom, ville));
    }

    public Optional<Centre> get(@NonNull Long id) {
        return centres.findById(id);
    }

    public List<Centre> getAllByVille(@NonNull String ville) {
        return centres.findAllByVille(ville);
    }

    public Optional<Centre> update(@NonNull Long id, String nom, String ville) {
        return get(id).map(centre -> {
            if (nom != null) {
                centre.setNom(nom);
            }
            if (ville != null) {
                centre.setVille(ville);
            }
            return centres.save(centre);
        });
    }

    public boolean delete(@NonNull Long id) {
        if (centres.existsById(id)) {
            centres.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
