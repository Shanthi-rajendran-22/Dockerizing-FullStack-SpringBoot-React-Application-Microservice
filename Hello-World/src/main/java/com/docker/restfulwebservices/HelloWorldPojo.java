package com.docker.restfulwebservices;

public class HelloWorldPojo {

	private String message;

	public HelloWorldPojo(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return String.format("HelloWorldPojo [message=%s]", message);
	}

}
