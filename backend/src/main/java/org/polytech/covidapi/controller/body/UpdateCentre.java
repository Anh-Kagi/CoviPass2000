package org.polytech.covidapi.controller.body;

import lombok.Getter;
import lombok.Setter;

public class UpdateCentre {
	@Getter
	@Setter
	private String nom;

	@Getter
	@Setter
	private String ville;
}

