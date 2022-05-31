package com.example.app_restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {
    private String message;

    public CustomerNotFoundException() {
    }

    public CustomerNotFoundException(String message) {
        this.message = message;
    }
}
