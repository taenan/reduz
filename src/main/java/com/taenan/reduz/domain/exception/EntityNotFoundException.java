package com.taenan.reduz.domain.exception;

import com.taenan.reduz.domain.model.Category;
import com.taenan.reduz.domain.model.Link;
import com.taenan.reduz.domain.model.User;

public class EntityNotFoundException extends DomainException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String message) {
		super(message);
	}

	public static <T> EntityNotFoundException throwIfEntityNotFound(Class<?> paramClass, Long id)
			throws EntityNotFoundException {
		String message = String.format("%s com código %d não encontrado(a)", getModelName(paramClass), id);
		throw new EntityNotFoundException(message);
	}

	public static <T> EntityNotFoundException throwIfEntityNotFound(Class<?> paramClass)
			throws EntityNotFoundException {
		String message = String.format("%s não encontrado(a)", getModelName(paramClass));
		throw new EntityNotFoundException(message);
	}

	// could not use paramClass.getSimpleName() because the model names must be in
	// portuguese
	private static String getModelName(Class<?> paramClass) {
		if (paramClass == User.class) {
			return "Usuário";
		}

		if (paramClass == Link.class) {
			return "Link";
		}

		if (paramClass == Category.class) {
			return "Categoria";
		}

		return "Entidade";
	}
}
