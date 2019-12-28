/**
 * 
 */
package com.reactiveworks.userproduct.csv.exceptions;

/**
 * This exception occurs when you perform add or update or remove operation on csv file
 * @author Sai Koushik Emani
 *
 */
public class DataSourceOperationNotSupportException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public DataSourceOperationNotSupportException() {
	}

	/**
	 * @param message
	 */
	public DataSourceOperationNotSupportException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DataSourceOperationNotSupportException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DataSourceOperationNotSupportException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public DataSourceOperationNotSupportException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
