package com.stockmanager.backend.response;

import java.time.LocalDateTime;

public class ApiResponse {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private Object obj;

    public ApiResponse(LocalDateTime timestamp, int status, String message, Object obj) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.obj = obj;
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

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public LocalDateTime getTimestamp(){
        return this.timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp){
        this.timestamp = timestamp;
    }
}
