package org.polytech.covidapi.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Reservation {
	@Id
	@Getter
	@Setter(AccessLevel.PROTECTED)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@Setter
	@ManyToOne
	private Centre centre;

	@Getter
	@Setter
	@ManyToOne
	private Patient patient;

	@Getter
	@Setter
	private boolean faite;

	protected Reservation() {
	}

	public Reservation(Centre centre, Patient patient) {
		setCentre(centre);
		setPatient(patient);
		setFaite(false);
	}
}
