package org.polytech.covidapi.service;

import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.model.Role;
import org.polytech.covidapi.model.Account;
import org.polytech.covidapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    public final UserRepository users;
    public final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean existsByIdAndRole(Long id, Role role) {
        return users.existsByIdAndRole(id, role);
    }

    public Optional<Account> findByIdAndRole(Long id, Role role) {
        return users.findByIdAndRole(id, role);
    }

    public Account create(String username, String password, Centre centre, Role role) {
        Account account = new Account(username, passwordEncoder.encode(password), centre, role);
        return users.save(account);
    }

    public Optional<Account> update(Long id, String username, String password, Centre centre, Role role) {
        Optional<Account> user_opt = users.findById(id);
        if (user_opt.isPresent()) {
            Account account = user_opt.get();
            if (username != null)
                account.setUsername(username);
            if (password != null)
                account.setHash(passwordEncoder.encode(password));
            if (centre != null)
                account.setCentre(centre);
            if (role != null)
                account.setRole(role);
            return Optional.of(users.save(account));
        }
        return Optional.empty();
    }

    public void deleteById(Long id) {
        users.deleteById(id);
    }
}
