package com.sl.shopingCart.exception;

public class AppException extends RuntimeException{
	private static final long serialVersionUID = 1L;


    
    public AppException() {
		super();
	}

	
	public AppException( String message) {
        super(message);
    }

    public AppException( String message, Throwable e) {
        super(message, e);
    }
}
