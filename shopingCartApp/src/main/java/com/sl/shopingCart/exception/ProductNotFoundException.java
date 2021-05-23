package com.sl.shopingCart.exception;


public class ProductNotFoundException extends AppException{

	private static final long serialVersionUID = 1L;

    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException( String message) {
        super(message);
    }

    public ProductNotFoundException( String message, Throwable e) {
        super(message, e);
    }
}
