package com.taenan.reduz.domain.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.taenan.reduz.domain.enums.Status;
import com.taenan.reduz.domain.model.Category;
import com.taenan.reduz.domain.model.User;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Page<Category> findByStatusAndUser(Pageable pageable, Status status, User user);

    List<Category> findByNameIgnoreCaseAndUser(String name, User user);
    
    List<Category> findByNameIgnoreCaseAndUserAndIdNot(String name, User user, Long id);
	
    Optional<Category> findByIdAndUser(Long id, User user);
    
    List<Category> findByUser(User user);
}
