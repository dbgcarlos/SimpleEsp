package com.pwc.exercise.exception;

public class SimpleEspException extends Exception{

	public SimpleEspException(String message, Throwable cause) {
		super(message, cause);
	}

	public SimpleEspException(String message) {
		super(message);
	}

	public SimpleEspException(Throwable cause) {
		super(cause);
	}

	public SimpleEspException() {
	}
	
	

}
