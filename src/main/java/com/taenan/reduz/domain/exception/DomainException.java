package com.taenan.reduz.domain.exception;

public class DomainException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DomainException(String message) {
		super(message);
	}
	
	public DomainException(String message, Throwable root) {
		super(message, root);
	}

}
