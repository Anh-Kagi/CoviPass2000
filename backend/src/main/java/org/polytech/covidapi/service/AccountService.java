package org.polytech.covidapi.service;

import lombok.NonNull;
import org.polytech.covidapi.model.Account;
import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.model.Role;
import org.polytech.covidapi.repository.AccountRepository;
import org.polytech.covidapi.repository.CentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    public final AccountRepository users;
    public final CentreRepository centres;
    public final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository users, CentreRepository centres, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.centres = centres;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean exists(@NonNull Long id, Role role) {
        return users.existsByIdAndRole(id, role);
    }

    public Optional<Account> find(@NonNull Long id, Role role) {
        return users.findByIdAndRole(id, role);
    }

    public Account create(@NonNull String username, @NonNull String password, Centre centre, Role role) {
        return users.save(new Account(username, passwordEncoder.encode(password), centre, role));
    }

    public Optional<Account> update(@NonNull Long id, String username, String password, Centre centre, Role role) {
        return users.findById(id).map(user -> {
            if (username != null)
                user.setUsername(username);
            if (password != null)
                user.setHash(passwordEncoder.encode(password));
            user.setCentre(centre);
            user.setRole(role);
            return users.save(user);
        });
    }

    public void delete(@NonNull Long id) {
        users.deleteById(id);
    }
}
