package org.polytech.covidapi.service;

import lombok.NonNull;
import org.polytech.covidapi.model.Patient;
import org.polytech.covidapi.model.Reservation;
import org.polytech.covidapi.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
		if (nom != null && prenom != null) {
			return patients.findAllByNomAndPrenom(nom, prenom);
		} else {
			return patients.findAllByNomOrPrenom(nom, prenom);
		}
	}

	public List<Reservation> getReservations(@NonNull Long id) {
		return patients.findById(id).map(patient -> {
			return reservations.getByPatient(patient);
		}).orElse(Arrays.asList());
	}

	public Optional<Patient> get(@NonNull Long id) {
		return patients.findById(id);
	}
}
