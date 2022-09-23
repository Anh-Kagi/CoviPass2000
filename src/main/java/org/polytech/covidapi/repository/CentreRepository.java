package org.polytech.covidapi.repository;

import org.polytech.covidapi.model.Centre;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CentreRepository extends JpaRepository<Centre, String>  {
    List<Centre> findAllByVilleCentre(String ville);
}
