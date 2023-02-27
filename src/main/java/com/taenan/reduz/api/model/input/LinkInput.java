package com.taenan.reduz.api.model.input;

import org.hibernate.validator.constraints.Length;

import com.taenan.reduz.api.model.input.idinput.CategoryIdInput;
import com.taenan.reduz.domain.validator.ValidUrl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LinkInput {

	@Length(max = 255)
	private String title;
	
	@ValidUrl
	@NotBlank
	@NotNull
	private String original;
	
	@Length(max = 255)
	private String favicon;

	@Valid
	@NotNull
	private CategoryIdInput category;
	
//	@Valid
//	@NotNull
//	private UserIdInput userIdInput;
}
