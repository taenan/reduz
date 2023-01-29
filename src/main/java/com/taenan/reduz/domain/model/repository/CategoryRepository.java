package com.taenan.reduz.domain.model.repository;

import com.taenan.reduz.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
