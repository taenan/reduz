package com.taenan.reduz.domain.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.taenan.reduz.domain.enums.Status;
import com.taenan.reduz.domain.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Page<Category> findByStatus(Pageable pageable, Status status);

    List<Category> findByNameIgnoreCase(String name);
    
    List<Category> findByNameIgnoreCaseAndIdNot(String name, Long id);
	
}
