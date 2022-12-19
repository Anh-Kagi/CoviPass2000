package org.polytech.covidapi.repository;

import org.polytech.covidapi.model.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Medecin findMedecinById(Long id);
}