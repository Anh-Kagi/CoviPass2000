package org.polytech.covidapi.service;

import org.polytech.covidapi.model.BaseUser;
import org.polytech.covidapi.repository.UserRepository;
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
    private final UserRepository<BaseUser> users;

    @Autowired
    public AuthService(UserRepository<BaseUser> users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<BaseUser> user = users.findByUsername(username);

        if (user.isPresent()) {
            return new User(user.get().getUsername(), user.get().getHash(), List.of());
        }
        throw new UsernameNotFoundException(username + "not found");
    }
}
