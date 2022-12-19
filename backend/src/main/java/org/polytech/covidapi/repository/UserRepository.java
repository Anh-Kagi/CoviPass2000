package org.polytech.covidapi.repository;

import org.polytech.covidapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {
    Optional<User> findByUsername(String username);
}