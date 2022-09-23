package org.polytech.covidapi.repository;

import org.polytech.covidapi.model.Centre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentreRepository extends JpaRepository<Centre, Integer>  {
}
