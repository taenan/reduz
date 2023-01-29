package com.taenan.reduz.api.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taenan.reduz.api.assembler.CategoryModelAssembler;
import com.taenan.reduz.api.model.CategoryModel;
import com.taenan.reduz.domain.model.Category;
import com.taenan.reduz.domain.model.repository.CategoryRepository;

@RestController
@RequestMapping(path = "/api/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

	private CategoryModelAssembler categoryModelAssembler;
	private CategoryRepository categoryRepository;

	public CategoryController(CategoryRepository categoryRepository, CategoryModelAssembler categoryModelAssembler) {
		super();
		this.categoryRepository = categoryRepository;
		this.categoryModelAssembler = categoryModelAssembler;
	}

	@GetMapping
	public CollectionModel<CategoryModel> listar() {
		List<Category> categories = categoryRepository.findAll();
		return categoryModelAssembler.toCollectionModel(categories);
	}

}
