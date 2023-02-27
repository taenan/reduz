package com.taenan.reduz.domain.model.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordChangeDTO {

	@NotBlank
	@NotNull
	private String currentPassword;

	@NotBlank
	@NotNull
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).*$") //must have at least one uppercase letter, one lowercase letter and one number
	@Length(min = 8)
	private String newPassword;

}
