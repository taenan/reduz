package com.taenan.reduz.domain.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taenan.reduz.domain.model.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {

	List<Link> findByOriginalIgnoreCase(String original);

	Optional<Link> findBySlug(String slug);
		
}
