package org.polytech.covidapi.service;

import org.polytech.covidapi.model.BaseUser;
import org.polytech.covidapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        Optional<BaseUser> user_opt = users.findByUsername(username);

        if (user_opt.isPresent()) {
            BaseUser user = user_opt.get();
            GrantedAuthority gauth = new SimpleGrantedAuthority(user.getRole());
            return new User(user.getUsername(), user.getHash(), List.of(gauth));
        }
        throw new UsernameNotFoundException(username + "not found");
    }
}
