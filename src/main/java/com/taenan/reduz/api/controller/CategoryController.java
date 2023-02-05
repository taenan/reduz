package com.taenan.reduz.api.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.taenan.reduz.api.assembler.CategoryModelAssembler;
import com.taenan.reduz.api.model.CategoryModel;
import com.taenan.reduz.api.model.input.CategoryInput;
import com.taenan.reduz.domain.model.Category;
import com.taenan.reduz.domain.model.repository.CategoryRepository;
import com.taenan.reduz.domain.service.CategoryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

	private CategoryModelAssembler categoryModelAssembler;
	private CategoryRepository categoryRepository;
	private CategoryService categoryService;

	@GetMapping
	public CollectionModel<CategoryModel> listar() {
		List<Category> categories = categoryRepository.findAll();
		return categoryModelAssembler.toCollectionModel(categories);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public CategoryModel adicionar(@RequestBody @Valid CategoryInput categoryInput) {
		return categoryService.create(categoryInput);
	}
	
	@PutMapping("/{id}")
	public CategoryModel atualizar(@PathVariable Long id,
			@RequestBody @Valid CategoryInput categoryInput) {
		return categoryService.update(id, categoryInput);
	}

}
