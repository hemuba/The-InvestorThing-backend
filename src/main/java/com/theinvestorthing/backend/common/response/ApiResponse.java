package com.theinvestorthing.backend.common.response;

import java.time.LocalDateTime;

public class ApiResponse<T>{
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private T object;
    private String traceId;

    public ApiResponse(LocalDateTime timestamp, int status, String message, T object, String traceId) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.object = object;
        this.traceId = traceId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getTraceId(){
        return this.traceId;
    }

    public void setTraceId(String traceId){
        this.traceId = traceId;
    }
}
