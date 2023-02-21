package com.taenan.reduz.domain.exception;

public class CategoryNotFoundException extends EntityNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CategoryNotFoundException(String message) {
		super(message);
	}
	
	public CategoryNotFoundException(Long id) {
		this(String.format("Link com código %d não encontrado", id));
	}
	
}
