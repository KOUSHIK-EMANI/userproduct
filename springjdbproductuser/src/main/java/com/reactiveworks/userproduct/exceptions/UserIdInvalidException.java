/**
 * 
 */
package com.reactiveworks.userproduct.exceptions;

/**
 * This UserIdInvalidException occurs when you enter the incorrect userid or
 * userid which does not exist
 * 
 * @author Sai Koushik Emani
 *
 */
public class UserIdInvalidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public UserIdInvalidException() {
	}

	/**
	 * @param message
	 */
	public UserIdInvalidException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public UserIdInvalidException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UserIdInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public UserIdInvalidException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
