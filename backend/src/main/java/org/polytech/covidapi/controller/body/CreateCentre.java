package org.polytech.covidapi.controller.body;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class CreateCentre {
	@Getter
	@Setter
	@NonNull
	private String nom;

	@Getter
	@Setter
	@NonNull
	private String ville;
}
