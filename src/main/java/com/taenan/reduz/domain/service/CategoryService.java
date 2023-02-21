package com.taenan.reduz.domain.service;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.taenan.reduz.api.ResourceUriHelper;
import com.taenan.reduz.api.assembler.CategoryInputDisassembler;
import com.taenan.reduz.api.assembler.CategoryModelAssembler;
import com.taenan.reduz.api.model.CategoryModel;
import com.taenan.reduz.api.model.input.CategoryInput;
import com.taenan.reduz.domain.enums.Status;
import com.taenan.reduz.domain.exception.CategoryNotFoundException;
import com.taenan.reduz.domain.exception.DomainException;
import com.taenan.reduz.domain.model.Category;
import com.taenan.reduz.domain.model.repository.CategoryRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Validated
public class CategoryService {

	private CategoryRepository categoryRepository;
	private CategoryModelAssembler categoryModelAssembler;
	private CategoryInputDisassembler categoryInputDisassembler;

	public CollectionModel<CategoryModel> findAll() {
		List<Category> categories = categoryRepository.findAll();
		return categoryModelAssembler.toCollectionModel(categories);
	}

	public CategoryModel create(@Valid CategoryInput categoryInput) {

		categoryRepository.findByNameIgnoreCase(categoryInput.getName()).stream().findAny().ifPresent(c -> {
			throw new DomainException("Uma categoria com o nome " + categoryInput.getName() + " já existe.");
		});

		Category category = categoryInputDisassembler.toDomainObject(categoryInput);
		category.setStatus(Status.ACTIVE);
		category = categoryRepository.save(category);
		CategoryModel categoryModel = categoryModelAssembler.toModel(category);

		ResourceUriHelper.addUriInResponseHeader(categoryModel.getId());

		return categoryModel;
	}

	public CategoryModel update(@Positive @NotNull Long id, @Valid CategoryInput categoryInput) {
		checkNameExists(categoryInput.getName(), id);

		Category actual = this.findOrFail(id);
		categoryInputDisassembler.copyToDomainObject(categoryInput, actual);
		actual = categoryRepository.save(actual);
		return categoryModelAssembler.toModel(actual);
	}

	public Category findOrFail(Long categoryId) {
		return categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
	}

	public void delete(@Positive @NotNull Long id) {
		categoryRepository.delete(categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id)));
	}
	
	private void checkNameExists(String name, Long id) {
		categoryRepository.findByNameIgnoreCaseAndIdNot(name, id).stream().findAny().ifPresent(c -> {
			throw new DomainException("Uma categoria com o nome " + name + " já existe.");
		});
	}
}
