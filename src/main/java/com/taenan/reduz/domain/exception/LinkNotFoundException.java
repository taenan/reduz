package com.taenan.reduz.domain.exception;

public class LinkNotFoundException extends EntityNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LinkNotFoundException(String message) {
		super(message);
	}
	
	public LinkNotFoundException(Long id) {
		this(String.format("Link com código %d não encontrado", id));
	}
	
	public LinkNotFoundException() {
		this(String.format("Link não encontrado"));
	}
	
}
