/**
 * 
 */
package com.reactiveworks.userproduct.db.exceptions;

/**
 * This exception occurs when unable to connect to database
 * @author Sai Koushik Emani
 *
 */
public class DataBaseAccessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public DataBaseAccessException() {
	}

	/**
	 * @param message
	 */
	public DataBaseAccessException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DataBaseAccessException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DataBaseAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public DataBaseAccessException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
