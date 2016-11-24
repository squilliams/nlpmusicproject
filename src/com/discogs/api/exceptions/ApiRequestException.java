package com.discogs.api.exceptions;

public class ApiRequestException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2989157677725949636L;

	public ApiRequestException(String s) {
		super(s);
	}
	
	public ApiRequestException(Throwable t) {
		super(t);
	}
}
