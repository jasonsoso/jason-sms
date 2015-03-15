package com.jason.sms.util;

public class JsonMessage {

	public enum State {
		SUCCESS, WARNNING, ERROR
	}

	private JsonMessage() {
	}

	private State state;
	private String message;

	public State getState() {
		return state;
	}

	public String getMessage() {
		return message;
	}

	public static JsonMessage one() {
		return new JsonMessage();
	}

	public JsonMessage success() {
		this.state = State.SUCCESS;
		return this;
	}

	public JsonMessage warnning() {
		this.state = State.WARNNING;
		return this;
	}

	public JsonMessage error() {
		this.state = State.ERROR;
		return this;
	}

	public JsonMessage message(String message) {
		this.message = message;
		return this;
	}
}
