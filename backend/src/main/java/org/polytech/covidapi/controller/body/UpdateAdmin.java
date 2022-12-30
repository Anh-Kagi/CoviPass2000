package org.polytech.covidapi.controller.body;

import lombok.Getter;
import lombok.Setter;

public class UpdateAdmin {
	@Getter
	@Setter
	private String username;

	@Getter
	@Setter
	private String password;

	@Getter
	@Setter
	private Long centre;
}
