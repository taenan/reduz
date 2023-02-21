package com.taenan.reduz.api.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.taenan.reduz.api.model.CategoryModel;
import com.taenan.reduz.api.model.input.CategoryInput;
import com.taenan.reduz.domain.service.CategoryService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

	private CategoryService categoryService;

	@GetMapping
	public CollectionModel<CategoryModel> findAll() {
		return categoryService.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public CategoryModel create(@RequestBody @Valid CategoryInput categoryInput) {
		return categoryService.create(categoryInput);
	}

	@PutMapping("/{id}")
	public CategoryModel update(@PathVariable Long id, @RequestBody @Valid CategoryInput categoryInput) {
		return categoryService.update(id, categoryInput);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable @Positive @NotNull Long id) {
		categoryService.delete(id);
	}

}
