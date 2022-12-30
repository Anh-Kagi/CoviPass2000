package org.polytech.covidapi.controller.body;

import lombok.Getter;
import lombok.Setter;

public class ReadPatient {
	@Getter
	@Setter
	private String nom;

	@Getter
	@Setter
	private String prenom;
}
