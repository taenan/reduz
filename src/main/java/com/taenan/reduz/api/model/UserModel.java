package com.taenan.reduz.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "users", itemRelation = "user")
@Setter
@Getter
public class UserModel extends RepresentationModel<UserModel> {
	
    private Long id;
    private String username;

}
