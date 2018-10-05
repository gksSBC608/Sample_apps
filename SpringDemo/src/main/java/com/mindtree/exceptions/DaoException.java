/**
 * 
 */
package com.mindtree.exceptions;

/**
 * 
 * @author M1030608
 *
 */
public class DaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1964553579083777590L;

	/**
	 * 
	 */
	public DaoException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public DaoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public DaoException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DaoException(Throwable cause) {
		super(cause);
	}

}
