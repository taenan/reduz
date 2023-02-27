package com.taenan.reduz.api.model.input.idinput;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserIdInput {

	@NotNull
	private Long id;
	
}
