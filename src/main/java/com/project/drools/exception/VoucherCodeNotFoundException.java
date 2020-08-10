package com.project.drools.exception;

public class VoucherCodeNotFoundException extends RuntimeException{

	public VoucherCodeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public VoucherCodeNotFoundException(String message) {
		super(message);
	}

	public VoucherCodeNotFoundException(Throwable cause) {
		super(cause);
	}
	
	

}
