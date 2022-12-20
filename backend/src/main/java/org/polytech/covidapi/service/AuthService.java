package org.polytech.covidapi.service;

import org.polytech.covidapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    private final AccountRepository users;

    @Autowired
    public AuthService(AccountRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return users.findByUsername(username)
				.map(user -> new User(user.getUsername(), user.getHash(), user.getRole().getAuthorities()))
				.orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
	}
}
