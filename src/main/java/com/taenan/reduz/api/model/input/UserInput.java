package com.taenan.reduz.api.model.input;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInput {
	
	@NotBlank
	@NotNull
	@Length(max = 50)
    private String username;
	
	@NotBlank
	@NotNull
	@Length(max = 75)
    private String password;
	
}
