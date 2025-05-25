package com.theinvestorthing.backend.common.exception;

import com.theinvestorthing.backend.common.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<NotFoundException>> handlerNotFound(NotFoundException exception){
        logger.error("404 - {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<NotFoundException>(LocalDateTime.now(), 404, exception.getMessage(), null));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<BadRequestException>> handlerBadRequests(BadRequestException exception){
        logger.error("400 - {}", exception.getMessage());
        return ResponseEntity.badRequest().body(new ApiResponse<BadRequestException>(LocalDateTime.now(), 400, exception.getMessage(), null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handlerDTOValidationErrors(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
        String message = "Validation failed for " + errors.size() + " field(s)";
        logger.error("400 - validation failed for {} filed(s).", errors.size());
        return ResponseEntity.badRequest().body(new ApiResponse<Map<String, String>>(LocalDateTime.now(), 400, message, errors));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handlerParamValidationErrors(ConstraintViolationException exception){
        Map<String, String> errors = new HashMap<>();
       exception.getConstraintViolations().forEach(v -> errors.put(v.getPropertyPath().toString(), v.getMessage()));
       String message = "Validation failed for " + errors.size() + " field(s)";
       logger.error("400 - validation failed for {} filed(s).", errors.size());
       return ResponseEntity.badRequest().body(new ApiResponse<Map<String, String>>(LocalDateTime.now(), 400, message, errors));
    }


    @ExceptionHandler(MultiStatusException.class)
    public ResponseEntity<ApiResponse<MultiStatusException>> handlerMultiStatusException(MultiStatusException exception){
        logger.error("207 - {}", exception.getMessage());
        return ResponseEntity.status(207).body(new ApiResponse<MultiStatusException>(LocalDateTime.now(), 207, exception.getMessage(), null));
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ApiResponse<UnprocessableEntityException>> hanlderUnprocessableEntityException(UnprocessableEntityException exception){
        logger.error("422 - {}", exception.getMessage());
        return ResponseEntity.status(422).body(new ApiResponse<UnprocessableEntityException>(LocalDateTime.now(), 422, exception.getMessage(), null));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handlerGenericExceptions(Exception exception){
        logger.error("500 an error occurred - please contact support or try again later.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>(LocalDateTime.now(), 500, "An unexpected error occurred", "Please contact support or try again later"));
    }


}
