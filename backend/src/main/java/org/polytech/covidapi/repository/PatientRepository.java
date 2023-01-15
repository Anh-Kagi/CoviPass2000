package org.polytech.covidapi.repository;

import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	@Query("SELECT DISTINCT p FROM Patient p, Reservation r WHERE r.centre = :centre AND r.patient = p")
	List<Patient> findAllByCentre(Centre centre);

	@Query("SELECT p FROM Patient p WHERE upper(p.nom) = :nom AND p.prenom = :prenom AND p.mail = :mail AND p.telephone = :phone")
	Optional<Patient> findPatient(@Param("nom") String nom, @Param("prenom") String prenom, @Param("mail") String mail, @Param("phone") String telephone);
}
