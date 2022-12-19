package org.polytech.covidapi.repository;

import org.polytech.covidapi.model.Centre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CentreRepository extends JpaRepository<Centre, Long> {

    Optional<Centre> findCentreById(Long id);

    List<Centre> findAllByVille(String ville);
}
