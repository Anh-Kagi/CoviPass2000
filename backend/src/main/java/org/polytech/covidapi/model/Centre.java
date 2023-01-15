package org.polytech.covidapi.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Centre {
	@Id
	@Getter
	@Setter(AccessLevel.PROTECTED)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@Setter
	private String nom;

	@Getter
	@Setter
	private String ville;

	@Getter
	@Setter
	private String adresse;

	// TODO: add cascading

	public Centre(String nom, String ville, String adresse) {
		this.nom = nom;
		this.ville = ville;
		this.adresse = adresse;
	}

	protected Centre() {
	}
}
