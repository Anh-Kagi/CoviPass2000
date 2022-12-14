package org.polytech.covidapi.service;

import lombok.NonNull;
import org.polytech.covidapi.model.Patient;
import org.polytech.covidapi.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
	private final PatientRepository patients;

	@Autowired
	public PatientService(PatientRepository patients) {
		this.patients = patients;
	}

	public List<Patient> getAll(String nom, String prenom) {
		if (nom != null && prenom != null) {
			return patients.findAllByNomAndPrenom(nom, prenom);
		} else {
			return patients.findAllByNomOrPrenom(nom, prenom);
		}
	}

	public Optional<Patient> get(@NonNull Long id) {
		return patients.findById(id);
	}
}
