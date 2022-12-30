package org.polytech.covidapi.controller.body;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class CreateMedecin {
	@Getter
	@Setter
	@NonNull
	private String password;

	@Getter
	@Setter
	@NonNull
	private Long centre;

	@Getter
	@Setter
	@NonNull
	private String username;
}
