package com.template.globalException;

public class DuplicateLeadException extends RuntimeException {
   
	private static final long serialVersionUID = 1L;

	public DuplicateLeadException(String message) {
        super(message);
    }
}