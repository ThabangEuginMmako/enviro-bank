package com.enviro.assessment.grad001.thabangmmako.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountProfileRepository extends JpaRepository<com.enviro.assessment.grad001.thabangmmako.model.AccountProfile, Long> {
    Optional<AccountProfile> findByNameAndSurname(String name, String surname);
}
