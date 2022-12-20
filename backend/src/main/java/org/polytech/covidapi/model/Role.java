package org.polytech.covidapi.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public enum Role {
	MEDECIN(null),
	ADMIN(MEDECIN),
	SUPER_ADMIN(ADMIN);

	private final Role parent;

	Role(Role inherit) {
		this.parent = inherit;
	}

	public List<String> getRoles() {
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_" + this.name());
		if (parent != null)
			roles.addAll(parent.getRoles());
		return roles;
	}

	public List<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String role : getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
}
