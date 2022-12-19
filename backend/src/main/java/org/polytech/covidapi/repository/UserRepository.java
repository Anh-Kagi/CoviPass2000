package org.polytech.covidapi.repository;

import org.polytech.covidapi.model.Account;
import org.polytech.covidapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);

    boolean existsByIdAndRole(Long id, Role role);

    Optional<Account> findByIdAndRole(Long id, Role role);
}