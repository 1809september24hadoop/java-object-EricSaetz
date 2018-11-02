package com.revature.exceptions;

public class MissingContentException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4418579282023884521L;

	public MissingContentException() {
		super("Oops! This part of the game isn't added yet. Guess I'll just crash! ¯\\_(ツ)_/¯");
	}

}
