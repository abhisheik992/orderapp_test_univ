package com.orderapp.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

	private Date timeStamp = new Date();
	private String message;
	private String details;
	private HttpStatus status;

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(Date timeStamp, String message, String details, HttpStatus status) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
		this.status = status;
	}

	public ErrorResponse(String message, String details, HttpStatus status) {
		super();
		this.message = message;
		this.details = details;
		this.status = status;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
