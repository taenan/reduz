package com.taenan.reduz.domain.exception;

public class EntityInUseException extends DomainException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EntityInUseException(String message) {
		super(message);
	}
	
}
