package com.stockmanager.backend.exceptions;


import com.stockmanager.backend.response.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@ControllerAdvice
public class GlobalControllerAdvice {
    private final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> hanlderNotFoundException(NotFoundException exception){
        logger.error("Error: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(LocalDateTime.now(), 404, exception.getMessage(), null));

    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse> handlerBadRequest(BadRequestException exception){
        logger.error("Error: {}", exception.getMessage());
        return ResponseEntity.badRequest().body(new ApiResponse(LocalDateTime.now(), 400, exception.getMessage(), null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> hanlderMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
        String message = "Validation failed for " + errors.size() + " field(s)";
        return ResponseEntity.badRequest().body(new ApiResponse(LocalDateTime.now(), 400, message, errors));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> handlerConstraintViolationExceptin(ConstraintViolationException exception){
        Map<String, String> errors = new HashMap<>();
        exception.getConstraintViolations().forEach(e -> errors.put(e.getPropertyPath().toString(), e.getMessage()));
        String message = "Validation failed for " + errors.size() + " field(s)";
        logger.error("error {}", message);
        return ResponseEntity.badRequest().body(new ApiResponse(LocalDateTime.now(), 400, message, errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handlerGenericExceptions(Exception exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(LocalDateTime.now(), 500, "An unexpected error occurred", "Please contact support or try again later."));
    }
}
