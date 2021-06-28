package com.flamexander.spring.security.cookbook.oauth2.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ServiceError {
    private int status;
    private String message;
    private Date timestamp;

    public ServiceError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
