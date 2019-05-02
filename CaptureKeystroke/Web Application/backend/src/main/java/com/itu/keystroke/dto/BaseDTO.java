package com.itu.keystroke.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BaseDTO implements Serializable {

    private String message;
    private int appErrorCode;
    private HttpStatus status;

    public BaseDTO() {
    }

    public BaseDTO(HttpStatus status, String message, int appErrorCode) {
        this.status = status;
        this.message = message;
        this.appErrorCode = appErrorCode;
    }

    public int getStatus() {
        return status.value();
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAppErrorCode() {
        return appErrorCode;
    }

    public void setAppErrorCode(int appErrorCode) {
        this.appErrorCode = appErrorCode;
    }
}