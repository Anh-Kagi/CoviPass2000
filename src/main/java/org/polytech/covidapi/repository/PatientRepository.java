package org.polytech.covidapi.repository;

import org.polytech.covidapi.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAllByNomOrPrenom(String nom, String prenom);

    List<Patient> findAllByNomAndPrenom(String nom, String prenom);

    @Query("SELECT p FROM Patient p WHERE p.nom = :nom AND p.prenom = :prenom AND p.mail = :mail AND p.telephone = :phone")
    Patient findPatient(@Param("nom") String nom, @Param("prenom") String prenom, @Param("mail") String mail, @Param("phone") long telephone);

    Patient findPatientById(Long id);
}
