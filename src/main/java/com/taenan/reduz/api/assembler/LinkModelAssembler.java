package com.taenan.reduz.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.taenan.reduz.api.controller.LinkController;
import com.taenan.reduz.api.model.LinkModel;
import com.taenan.reduz.domain.model.Link;

@Component
public class LinkModelAssembler extends RepresentationModelAssemblerSupport<Link, LinkModel> {

	private ModelMapper modelMapper;

	public LinkModelAssembler(ModelMapper modelMapper) {
		super(LinkController.class, LinkModel.class);
		this.modelMapper = modelMapper;
	}

	@Override
	public LinkModel toModel(Link entity) {
		LinkModel entityModel = createModelWithId(entity.getId(), entity);
		modelMapper.map(entity, entityModel);
		return entityModel;
	}

}
