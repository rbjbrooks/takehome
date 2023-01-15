package com.example.takehome.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class ServiceDownException extends RuntimeException{

    /**
     * @param message
     */
    public ServiceDownException(String message) {
        super(message);
    }
    
}
