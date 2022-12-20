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

	public boolean canAlter(@NonNull Account admin, @NonNull Centre centre) {
		return admin.getRole() == Role.SUPER_ADMIN || (admin.getRole() == Role.ADMIN && admin.getCentre().getId().equals(centre.getId()));
	}

	public Account create(@NonNull String username, @NonNull String password, @NonNull Centre centre) {
		return users.create(username, password, centre, Role.ADMIN);
	}

	public Optional<Account> get(@NonNull Long id) {
		return users.find(id, Role.ADMIN)
				.or(() -> users.find(id, Role.ADMIN));
	}

	public Optional<Account> get(@NonNull String username) {
		return users.find(username, Role.ADMIN)
				.or(() -> users.find(username, Role.SUPER_ADMIN));
	}

	public Account update(@NonNull Account account, String username, String password, Centre centre) {
		return users.update(account, username, password, centre, null);
	}

	public void delete(@NonNull Account account) {
		users.delete(account);
	}
}
