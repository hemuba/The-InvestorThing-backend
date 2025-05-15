package com.theinvestorthing.backend.common.exception;

public class UnprocessableEntityException extends RuntimeException{
    public UnprocessableEntityException(String message){
        super(message);
    }
}
