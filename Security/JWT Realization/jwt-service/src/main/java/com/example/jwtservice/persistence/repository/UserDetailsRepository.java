package com.example.jwtservice.persistence.repository;

import com.example.jwtservice.persistence.domain.UserDetails;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserDetailsRepository extends ListCrudRepository<UserDetails, Long> {

    Optional<UserDetails> findByUsername(String username);
}
