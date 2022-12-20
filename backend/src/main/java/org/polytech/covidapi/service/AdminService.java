package org.polytech.covidapi.service;

import lombok.NonNull;
import org.polytech.covidapi.model.Account;
import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    private final AccountService users;

    @Autowired
    public AdminService(AccountService users) {
        this.users = users;
    }

    public Account create(@NonNull String username, @NonNull String password, @NonNull Centre centre) {
        return users.create(username, password, centre, Role.ADMIN);
    }

    public Optional<Account> get(@NonNull Long id) {
        return users.find(id, Role.ADMIN);
    }

    public Optional<Account> update(@NonNull Long id, String username, String password, Centre centre) {
        if (users.exists(id, Role.ADMIN))
            return users.update(id, username, password, centre, null);
        return Optional.empty();
    }

    public boolean delete(@NonNull Long id) {
        if (users.exists(id, Role.ADMIN)) {
            users.delete(id);
            return true;
        }
        return false;
    }
}
