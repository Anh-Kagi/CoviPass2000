package org.polytech.covidapi.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Patient {
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
	private String prenom;

	@Getter
	@Setter
	private String mail;

	@Getter
	@Setter
	private String telephone;

	public Patient(String nom, String prenom, String mail, String telephone) {
		setNom(nom);
		setPrenom(prenom);
		setMail(mail);
		setTelephone(telephone);
	}

	protected Patient() {
	}
}
