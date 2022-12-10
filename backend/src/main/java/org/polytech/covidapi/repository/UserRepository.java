package org.polytech.covidapi.repository;

import org.polytech.covidapi.model.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository<T extends BaseUser> extends JpaRepository<T, Long> {
    Optional<BaseUser> findByUsername(String username);
}