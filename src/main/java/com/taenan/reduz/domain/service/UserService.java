package com.taenan.reduz.domain.service;

import java.text.MessageFormat;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.taenan.reduz.api.assembler.UserInputDisassembler;
import com.taenan.reduz.api.assembler.UserModelAssembler;
import com.taenan.reduz.api.model.UserModel;
import com.taenan.reduz.api.model.input.UserInput;
import com.taenan.reduz.domain.enums.Status;
import com.taenan.reduz.domain.exception.DomainException;
import com.taenan.reduz.domain.exception.EntityNotFoundException;
import com.taenan.reduz.domain.model.User;
import com.taenan.reduz.domain.model.dto.PasswordChangeDTO;
import com.taenan.reduz.domain.model.repository.UserRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Valid
@Service
@AllArgsConstructor
public class UserService implements UserDetailsManager {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	private UserModelAssembler userModelAssembler;
	private UserInputDisassembler userInputDisassembler;

	private static final String MSG_USERNAME_EXISTS = "Este nome de usuário já existe";

	@Override
	public void createUser(@Valid UserDetails user) {
		((User) user).setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save((User) user);
	}

	@Override
	public void updateUser(UserDetails user) {

	}

	@Override
	public void deleteUser(String username) {

	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {

	}

	@Override
	public boolean userExists(@NotNull @NotBlank String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(@NotNull @NotBlank String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException(MessageFormat.format("username {0} not found", username)));
	}

	public void register(@Valid UserInput userInput) {
		User user = userInputDisassembler.toDomainObject(userInput);

		userRepository.findByUsername(userInput.getUsername()).stream().findAny().ifPresent(c -> {
			throw new DomainException(MSG_USERNAME_EXISTS);
		});

		user.setStatus(Status.ACTIVE);
		this.createUser(user);
	}

	public UserModel changePasswordImpl(@Positive Long id, @Valid PasswordChangeDTO passwordChangeDTO) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> EntityNotFoundException.throwIfEntityNotFound(User.class, id));

		if (!this.checkPassword(user, passwordChangeDTO.getCurrentPassword())) {
			throw new DomainException("Senha inválida");
		}

		String encodedPassword = passwordEncoder.encode(passwordChangeDTO.getNewPassword());
		user.setPassword(encodedPassword);
		userRepository.save(user);

		return userModelAssembler.toModel(user);
	}

	public UserModel findById(@Positive @NotNull Long id) {
		return userModelAssembler.toModel(userRepository.findById(id)
				.orElseThrow(() -> EntityNotFoundException.throwIfEntityNotFound(User.class, id)));
	}

	public User getUserLogged() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		User user = (User) auth.getPrincipal();
		return user;
//		return userRepository.findById(user.getId())
//				.orElseThrow(() -> EntityNotFoundException.throwIfEntityNotFound(User.class, user.getId()));
	}

	private boolean checkPassword(User user, String password) {
		return passwordEncoder.matches(password, user.getPassword());
	}
}
