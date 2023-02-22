package com.taenan.reduz.api.model;

import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.taenan.reduz.core.web.OffsetDateTimeDeserializer;
import com.taenan.reduz.core.web.OffsetDateTimeSerializer;
import com.taenan.reduz.domain.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "links", itemRelation = "link")
@Setter
@Getter
public class LinkModel extends RepresentationModel<LinkModel> {

	private Long id;
	private String original;
	private String slug;
	private String title;
	private String favicon;
	private int counter;
	
	@JsonSerialize(using = OffsetDateTimeSerializer.class)
    @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
	private OffsetDateTime createdAt;
	
	private Status status;
	private CategoryModel category;
}
