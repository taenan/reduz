package com.taenan.reduz.api.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taenan.reduz.api.model.UserModel;
import com.taenan.reduz.domain.model.User;
import com.taenan.reduz.domain.model.dto.PasswordChangeDTO;
import com.taenan.reduz.domain.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

	private UserService userService;

	@GetMapping("/{id}")
	@PreAuthorize("#user.id == #id")
	public UserModel findById(@AuthenticationPrincipal User user, @PathVariable @Positive @NotNull Long id) {
		return userService.findById(id);
	}

	@PutMapping("/{id}/changePassword")
	@PreAuthorize("#user.id == #id")
	public UserModel changePassword(@AuthenticationPrincipal User user, @PathVariable Long id,
			@RequestBody @Valid PasswordChangeDTO passwordChangeDTO) {
		return userService.changePasswordImpl(id, passwordChangeDTO);
	}
}
