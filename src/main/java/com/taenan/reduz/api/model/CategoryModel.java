package com.taenan.reduz.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")
@Setter
@Getter
public class CategoryModel extends RepresentationModel<CategoryModel> {

	private Long id;	
	private String name;
	private String icon;
}
