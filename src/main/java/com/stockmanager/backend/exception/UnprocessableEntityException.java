package com.stockmanager.backend.exception;

public class UnprocessableEntityException extends RuntimeException{
    public UnprocessableEntityException(String message){
        super(message);
    }
}
