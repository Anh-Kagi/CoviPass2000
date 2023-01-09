package org.polytech.covidapi.repository;

import org.polytech.covidapi.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    List<Patient> trouverParNomOuPrenom(String nomPatient, String prenomPatient);



}
