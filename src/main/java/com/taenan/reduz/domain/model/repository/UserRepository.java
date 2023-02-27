package com.taenan.reduz.domain.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taenan.reduz.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);

	boolean existsByUsername(String username);
}
