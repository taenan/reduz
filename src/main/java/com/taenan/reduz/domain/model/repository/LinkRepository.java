package com.taenan.reduz.domain.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.taenan.reduz.domain.model.Link;
import com.taenan.reduz.domain.model.User;

public interface LinkRepository extends JpaRepository<Link, Long> {

	List<Link> findByOriginalIgnoreCaseAndUser(String original, User user);

	Optional<Link> findBySlug(String slug);

	List<Link> findByUser(User user, Sort sort);
	
	Optional<Link> findByIdAndUser(Long id, User user);
		
}
