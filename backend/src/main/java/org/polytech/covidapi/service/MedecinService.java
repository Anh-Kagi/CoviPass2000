package org.polytech.covidapi.service;

import lombok.NonNull;
import org.polytech.covidapi.model.Account;
import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.model.Role;
import org.polytech.covidapi.repository.CentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedecinService {
    private final UserService users;
    private final CentreRepository centres;

    @Autowired
    public MedecinService(UserService users, CentreRepository centres) {
        this.users = users;
        this.centres = centres;
    }


    @Secured({"MEDECIN_CREATE"})
    public Optional<Account> create(@NonNull String username, @NonNull String password, @NonNull Long centreId, @NonNull Role role) {
        Optional<Centre> centre = centres.findCentreById(centreId);
        return centre.map(value -> users.create(username, password, value, role));
    }

    @Secured({"MEDECIN_READ"})
    public Optional<Account> get(Long id) {
        return users.findByIdAndRole(id, Role.MEDECIN);
    }

    @Secured({"MEDECIN_UPDATE"})
    public Optional<Account> update(Long id, String username, String password, Long centreId) {
        if (!users.existsByIdAndRole(id, Role.MEDECIN))
            return Optional.empty();
        Optional<Centre> centre_opt = centres.findCentreById(centreId);
        if (centre_opt.isPresent()) {
            Centre centre = centre_opt.get();
            return users.update(id, username, password, centre, null);
        }
        return Optional.empty();
    }
}
