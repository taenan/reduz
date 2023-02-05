package com.taenan.reduz.api.model.input;

import org.hibernate.validator.constraints.Length;	

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryInput {
	
	@NotBlank 
	@NotNull
	@Length(min = 5, max = 200)
	private String name;
	
	@NotBlank
	@NotNull
	@Length(max = 30)
	private String icon;
}
