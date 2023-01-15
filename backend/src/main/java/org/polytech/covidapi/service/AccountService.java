package org.polytech.covidapi.service;

import lombok.NonNull;
import org.polytech.covidapi.model.Account;
import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.model.Role;
import org.polytech.covidapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
	public final AccountRepository users;
	public final PasswordEncoder passwordEncoder;

	@Autowired
	public AccountService(AccountRepository users, PasswordEncoder passwordEncoder) {
		this.users = users;
		this.passwordEncoder = passwordEncoder;
	}

	public Optional<Account> find(@NonNull Long id, Role role) {
		return users.findByIdAndRole(id, role);
	}

	public Optional<Account> find(@NonNull String username) {
		return users.findByUsername(username);
	}

	public Optional<Account> find(@NonNull String username, Role role) {
		return users.findByUsernameAndRole(username, role);
	}

	public List<Account> list(Role role) {
		return users.findAllByRole(role);
	}

	public List<Account> list(Centre centre, Role role) {
		return users.findAllByCentreAndRole(centre, role);
	}

	public Account create(@NonNull String username, @NonNull String password, Centre centre, Role role) {
		return users.save(new Account(username, passwordEncoder.encode(password), centre, role));
	}

	public Account update(@NonNull Account user, String username, String password, Centre centre, Role role) {
		if (username != null)
			user.setUsername(username);
		if (password != null)
			user.setHash(passwordEncoder.encode(password));
		user.setCentre(centre);
		if (role != null)
			user.setRole(role);
		return users.save(user);
	}

	public void delete(@NonNull Account user) {
		users.delete(user);
	}
}
