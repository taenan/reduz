package com.taenan.reduz.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.taenan.reduz.api.controller.UserController;
import com.taenan.reduz.api.model.UserModel;
import com.taenan.reduz.domain.model.User;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {

	private ModelMapper modelMapper;

	public UserModelAssembler(ModelMapper modelMapper) {
		super(UserController.class, UserModel.class);
		this.modelMapper = modelMapper;
	}

	@Override
	public UserModel toModel(User entity) {
		UserModel entityModel = createModelWithId(entity.getId(), entity);
		modelMapper.map(entity, entityModel);
		return entityModel;
	}

}
