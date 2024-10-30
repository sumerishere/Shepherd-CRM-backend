package com.template.UserCommentDTO;

public class InvalidCommentException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidCommentException(String message) {
        super(message);
    }

    public InvalidCommentException(String message, Throwable cause) {
        super(message, cause);
    }
}