package com.orderapp.exceptions;

public class NullPointerCustomException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3957336124371844216L;
	private ErrorResponse errorResponse;

	public NullPointerCustomException(ErrorResponse errorResponse) {
		super();
		this.errorResponse = errorResponse;
	}

	public NullPointerCustomException() {
		super();
	}

	public NullPointerCustomException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NullPointerCustomException(String message, Throwable cause) {
		super(message, cause);
	}

	public NullPointerCustomException(String message) {
		super(message);
	}

	public NullPointerCustomException(Throwable cause) {
		super(cause);
	}

	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
}
