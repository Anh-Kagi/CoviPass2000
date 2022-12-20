package org.polytech.covidapi.service;

import lombok.NonNull;
import org.polytech.covidapi.model.Account;
import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedecinService {
	private final AccountService users;
	private final AdminService admins;

	@Autowired
	public MedecinService(AccountService users, AdminService admins) {
		this.users = users;
		this.admins = admins;
	}

	public boolean canAlter(@NonNull Account medecin, @NonNull Centre centre) {
		return admins.canAlter(medecin, centre) || (medecin.getRole().equals(Role.MEDECIN) && medecin.getCentre().equals(centre));
	}

	public Account create(@NonNull String username, @NonNull String password, @NonNull Centre centre) {
		return users.create(username, password, centre, Role.MEDECIN);
	}

	public Optional<Account> get(@NonNull Long id) {
		return users.find(id, Role.MEDECIN)
				.or(() -> admins.get(id));
	}

	public Optional<Account> get(@NonNull String username) {
		return users.find(username, Role.MEDECIN)
				.or(() -> admins.get(username));
	}

	public Account update(@NonNull Account user, String username, String password, Centre centre) {
		return users.update(user, username, password, centre, null);
	}
}
