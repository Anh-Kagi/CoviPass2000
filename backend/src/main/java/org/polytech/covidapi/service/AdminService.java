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
public class AdminService {
    private final UserService users;
    private final CentreRepository centres;

    @Autowired
    public AdminService(UserService users, CentreRepository centres) {
        this.users = users;
        this.centres = centres;
    }

    @Secured({"CENTRE_CREATE"})
    public Optional<Account> create(@NonNull String username, @NonNull String password, @NonNull Long centreId) {
        Optional<Centre> centre_opt = centres.findCentreById(centreId);
        if (centre_opt.isPresent()) {
            Centre centre = centre_opt.get();
            return Optional.ofNullable(users.create(username, password, centre, Role.ADMIN));
        }
        return Optional.empty();
    }

    public Optional<Account> get(Long id) {
        return users.findByIdAndRole(id, Role.ADMIN);
    }

    public Optional<Account> update(Long id, String username, String password, Long centreId) {
        Optional<Centre> centre_opt = centres.findCentreById(centreId);
        if (centre_opt.isPresent()) {
            Centre centre = centre_opt.get();
            return users.update(id, username, password, centre, null);
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        if (users.existsByIdAndRole(id, Role.ADMIN)) {
            users.deleteById(id);
            return true;
        }
        return false;
    }
}
