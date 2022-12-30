package org.polytech.covidapi.controller.body;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class CreateAdmin {
	@Getter
	@Setter
	@NonNull
	private String username;

	@Getter
	@Setter
	@NonNull
	private String password;

	@Getter
	@Setter
	@NonNull
	private Long centre;
}
