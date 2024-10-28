package com.template.ApiResponseClass;

import org.springframework.http.HttpStatus;


public class ApiResponse {
    private String message;
    private HttpStatus status;
    
    
    // Constructor, Getters, Setters
    public ApiResponse() {}
    
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

    
    
}
