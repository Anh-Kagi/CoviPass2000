package org.polytech.covidapi.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Account {
	@Id
	@Getter
	@Setter(AccessLevel.PROTECTED)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@Setter
	@Column(unique = true)
	private String username;

	@Getter
	@Setter
	private String hash;

	@Getter
	@Setter
	@ManyToOne
	private Centre centre;

	@Getter
	@Setter
	@Enumerated(EnumType.STRING)
	private Role role;

	public Account(String username, String hash, Centre centre, Role role) {
		setUsername(username);
		setHash(hash);
		setCentre(centre);
		setRole(role);
	}

	protected Account() {
	}
}
