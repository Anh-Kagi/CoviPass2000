package org.polytech.covidapi.service;

import lombok.NonNull;
import org.polytech.covidapi.model.Patient;
import org.polytech.covidapi.model.Reservation;
import org.polytech.covidapi.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
	private final PatientRepository patients;
	private final ReservationService reservations;

	@Autowired
	public PatientService(PatientRepository patients, ReservationService reservations) {
		this.patients = patients;
		this.reservations = reservations;
	}

	public List<Patient> getAll(String nom, String prenom) {
		List<Patient> matches = new LinkedList<>();
		if (nom != null && prenom != null) {
			matches.addAll(patients.findAllByNomIgnoreCaseAndPrenomIgnoreCase(nom, prenom));
		}
		if (nom != null ^ prenom != null) {
			matches.addAll(patients.findAllByNomIgnoreCaseOrPrenomIgnoreCase(nom, prenom));
		}
		return matches;
	}

	public List<Reservation> getReservations(@NonNull Long id) {
		return patients.findById(id)
				.map(reservations::getByPatient)
				.orElse(List.of());
	}

	public Optional<Patient> get(@NonNull Long id) {
		return patients.findById(id);
	}
}
