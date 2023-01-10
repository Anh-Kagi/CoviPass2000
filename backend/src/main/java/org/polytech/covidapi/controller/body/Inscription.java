package org.polytech.covidapi.controller.body;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class Inscription {
	@Getter
	@Setter
	@NonNull
	private Long centre;

	@Getter
	@Setter
	@NonNull
	private String nom;

	@Getter
	@Setter
	@NonNull
	private String prenom;

	@Getter
	@Setter
	@NonNull
	private String mail;

	@Getter
	@Setter
	@NonNull
	private String telephone;
}
