package com.jobsearchanalytics.exception;

import com.jobsearchanalytics.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // -----------------------------
    // VALIDATION ERRORS
    // -----------------------------
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return new ResponseEntity<>(
                new ErrorResponse(false, "Validation failed", errors),
                HttpStatus.BAD_REQUEST
        );
    }

    // -----------------------------
    // NOT FOUND (CUSTOM EXCEPTION)
    // -----------------------------
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {

        return new ResponseEntity<>(
                new ErrorResponse(false, ex.getMessage(), null),
                HttpStatus.NOT_FOUND
        );
    }

    // -----------------------------
    // GENERIC RUNTIME ERRORS
    // -----------------------------
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntime(RuntimeException ex) {

        return new ResponseEntity<>(
                new ErrorResponse(false, ex.getMessage(), null),
                HttpStatus.BAD_REQUEST
        );
    }

    // -----------------------------
    // FALLBACK
    // -----------------------------
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {

        return new ResponseEntity<>(
                new ErrorResponse(false, "Internal server error",
                        Map.of("error", ex.getMessage())),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
