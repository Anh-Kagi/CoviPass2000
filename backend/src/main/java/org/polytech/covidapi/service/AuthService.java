package org.polytech.covidapi.service;

import org.polytech.covidapi.model.Account;
import org.polytech.covidapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final AccountRepository users;

    @Autowired
    public AuthService(AccountRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> user_opt = users.findByUsername(username);

        if (user_opt.isPresent()) {
            Account account = user_opt.get();
            return new User(account.getUsername(), account.getHash(), List.of(new SimpleGrantedAuthority("ROLE_" + account.getRole().getName())));
        }
        throw new UsernameNotFoundException(username + "not found");
    }
}
