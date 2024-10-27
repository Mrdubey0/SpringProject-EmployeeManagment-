package com.jsp.exceptionClass;

public class InvalidPhone extends RuntimeException {
	
	private String message = "Invalid input";
	
	public InvalidPhone(String message) {
		super();
		this.message = message;
		
	}
	public InvalidPhone() {
		super();
		
		
	}
	@Override
	public String getMessage() {
		
		return this.message;
	}


}
