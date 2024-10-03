package com.centime.microservices.serviceC.exception;

public class InvalidJsonException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidJsonException(String message) {
        super(message);
    }
}
