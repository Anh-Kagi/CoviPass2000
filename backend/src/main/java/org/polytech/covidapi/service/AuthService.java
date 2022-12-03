package org.polytech.covidapi.service;

import org.polytech.covidapi.model.Admin;
import org.polytech.covidapi.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {
    private final AdminRepository admins;

    @Autowired
    public AuthService(AdminRepository admins) {
        this.admins = admins;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> user = admins.findByUsername(username);

        if (user.isPresent()) {
            return new User(user.get().getUsername(), user.get().getHash(), List.of());
        }
        throw new UsernameNotFoundException(username + "not found");
    }
}
