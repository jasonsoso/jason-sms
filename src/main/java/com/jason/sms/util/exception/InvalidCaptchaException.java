package com.jason.sms.util.exception;

public class InvalidCaptchaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidCaptchaException() {
		super();
	}

	public InvalidCaptchaException(String message) {
		super(message);
	}
}
