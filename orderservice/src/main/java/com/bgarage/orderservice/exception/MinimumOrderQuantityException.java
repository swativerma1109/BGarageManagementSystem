package com.bgarage.orderservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MinimumOrderQuantityException extends RuntimeException {
    public MinimumOrderQuantityException() {
        super();
    }

    public MinimumOrderQuantityException(String message, Throwable cause) {
        super(message, cause);
    }

    public MinimumOrderQuantityException(String message) {
        super(message);
    }

    public MinimumOrderQuantityException(Throwable cause) {
        super(cause);
    }
}
