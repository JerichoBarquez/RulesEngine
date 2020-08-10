package com.project.drools.exception;

import lombok.Getter;
import lombok.Setter;

public class VoucherCodeErrorResponse {
	
	@Getter @Setter
	private int status;
	@Getter @Setter
	private String message;
	@Getter @Setter
	private long timestamp;
	
	public VoucherCodeErrorResponse() {
		
	}
	
	public VoucherCodeErrorResponse(int status, String message, long timestamp) {
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
	}
	
	

}
