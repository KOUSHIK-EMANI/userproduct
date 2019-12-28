package com.reactiveworks.userproduct.csv.exceptions;

/**
 * This DataSourceReadException occurs when the file is not in specified
 * location or incorrect of file name
 * 
 * @author Sai Koushik Emani
 *
 */
public class DataSourceReadException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public DataSourceReadException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DataSourceReadException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public DataSourceReadException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DataSourceReadException(Throwable cause) {
		super(cause);
	}

}
