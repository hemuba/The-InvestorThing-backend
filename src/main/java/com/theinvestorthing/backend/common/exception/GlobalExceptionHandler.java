package com.theinvestorthing.backend.common.exception;

import com.theinvestorthing.backend.common.response.ApiResponse;
import com.theinvestorthing.backend.common.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerNotFound(NotFoundException exception, HttpServletRequest req){
        logger.error("404 - {}", exception.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("Error", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                req.getRequestURI(),
                errors));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handlerBadRequests(BadRequestException exception, HttpServletRequest req){
        Map<String, String> errors = new HashMap<>();
        errors.put("Error", exception.getMessage());
        logger.error("400 - {}", exception.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                req.getRequestURI(),
                errors));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerDTOValidationErrors(MethodArgumentNotValidException exception, HttpServletRequest req){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
        logger.error("400 - validation failed for {} filed(s).", errors.size());
        return ResponseEntity.badRequest().body(new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                req.getRequestURI(),
                errors));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handlerParamValidationErrors(ConstraintViolationException exception, HttpServletRequest req){
        Map<String, String> errors = new HashMap<>();
       exception.getConstraintViolations().forEach(v -> errors.put(v.getPropertyPath().toString(), v.getMessage()));
       logger.error("400 - validation failed for {} filed(s).", errors.size());
       return ResponseEntity.badRequest().body(new ErrorResponse(
               LocalDateTime.now(),
               HttpStatus.BAD_REQUEST.value(),
               exception.getMessage(),
               req.getRequestURI(),
               errors));
    }


    @ExceptionHandler(MultiStatusException.class)
    public ResponseEntity<ErrorResponse> handlerMultiStatusException(MultiStatusException exception, HttpServletRequest req){
        logger.error("207 - {}", exception.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("Error", exception.getMessage());
        return ResponseEntity.status(207).body(new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.MULTI_STATUS.value(),
                exception.getMessage(),
                req.getRequestURI(),
                errors));
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ErrorResponse> hanlderUnprocessableEntityException(UnprocessableEntityException exception, HttpServletRequest req){
        logger.error("422 - {}", exception.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("Error", exception.getMessage());
        return ResponseEntity.status(422).body(new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                exception.getMessage(),
                req.getRequestURI(),
                errors));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handlerGenericExceptions(Exception exception){
        logger.error("500 an error occurred - please contact support or try again later.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>(LocalDateTime.now(), 500, "An unexpected error occurred", "Please contact support or try again later"));
    }


}
