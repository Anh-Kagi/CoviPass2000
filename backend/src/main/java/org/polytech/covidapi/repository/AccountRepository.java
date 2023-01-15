package org.polytech.covidapi.repository;

import org.polytech.covidapi.model.Account;
import org.polytech.covidapi.model.Centre;
import org.polytech.covidapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByUsername(String username);

	Optional<Account> findByUsernameAndRole(String username, Role role);

	Optional<Account> findByIdAndRole(Long id, Role role);

	List<Account> findAllByRole(Role role);

	List<Account> findAllByCentreAndRole(Centre centre, Role role);
}