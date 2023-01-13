package org.polytech.covidapi.service;

import lombok.NonNull;
import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.model.Patient;
import org.polytech.covidapi.model.Reservation;
import org.polytech.covidapi.repository.PatientRepository;
import org.polytech.covidapi.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
	private final ReservationRepository reservations;
	private final PatientRepository patients;

	@Autowired
	public ReservationService(ReservationRepository reservations, PatientRepository patients) {
		this.reservations = reservations;
		this.patients = patients;
	}

	public Optional<Reservation> get(@NonNull Long id) {
		return reservations.findById(id);
	}

	public List<Reservation> getByPatient(@NonNull Patient patient) {
		return reservations.findByPatient(patient);
	}

	public Reservation create(@NonNull Centre centre,
	                          @NonNull String nom,
	                          @NonNull String prenom,
	                          @NonNull String mail,
	                          @NonNull String telephone) {
		Patient patient = patients.findPatient(nom, prenom, mail, telephone)
				.orElse(patients.save(new Patient(nom, prenom, mail, telephone)));
		return reservations.save(new Reservation(centre, patient));
	}

	public void delete(@NonNull Reservation reservation) {
		reservations.delete(reservation);
	}

	public Reservation validate(@NonNull Reservation reservation) {
		reservation.setFaite(true);
		return reservations.save(reservation);
	}
}
