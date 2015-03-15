package com.jason.sms.util.exception;

public class ValueInValidException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ValueInValidException() {
		super();
	}

	public ValueInValidException(String message) {
		super(message);
	}
	
	public ValueInValidException(Throwable cause) {
		super(cause);
	}
	
	public ValueInValidException(String message, Throwable cause) {
		super(message, cause);
	}
}
