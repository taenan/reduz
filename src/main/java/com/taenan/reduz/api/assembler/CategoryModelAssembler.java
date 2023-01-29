package com.taenan.reduz.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.taenan.reduz.api.controller.CategoryController;
import com.taenan.reduz.api.model.CategoryModel;
import com.taenan.reduz.domain.model.Category;

@Component
public class CategoryModelAssembler extends RepresentationModelAssemblerSupport<Category, CategoryModel> {

	private ModelMapper modelMapper;

	public CategoryModelAssembler(ModelMapper modelMapper) {
		super(CategoryController.class, CategoryModel.class);
		this.modelMapper = modelMapper;
	}

	@Override
	public CategoryModel toModel(Category entity) {
		CategoryModel entityModel = createModelWithId(entity.getId(), entity);
		modelMapper.map(entity, entityModel);
		return entityModel;
	}

}
