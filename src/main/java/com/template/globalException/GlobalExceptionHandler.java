package com.template.globalException;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.template.ApiResponseClass.ApiResponse;
import com.template.UserCommentDTO.InvalidCommentException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	
	
    @ExceptionHandler(InvalidCommentException.class)
    public ResponseEntity<ApiResponse> handleInvalidCommentException(InvalidCommentException ex) {
    	
        log.error("Invalid comment exception: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(ex.getMessage(),HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(LeadNotFoundException.class)
    public ResponseEntity<ApiResponse> handleLeadNotFoundException(LeadNotFoundException ex) {
    	
        log.error("Lead not found exception: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(ex.getMessage(),HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationException(MethodArgumentNotValidException ex) {
    	
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
            .collect(Collectors.toList());

        log.error("Validation failed: {}", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ApiResponse("Validation failed", HttpStatus.BAD_REQUEST));
    }

    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(Exception ex) {
    	
        log.error("Unexpected error occurred", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiResponse("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR));
    }
}