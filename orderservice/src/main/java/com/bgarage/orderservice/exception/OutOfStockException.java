package com.bgarage.orderservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OutOfStockException extends RuntimeException {
    public OutOfStockException() {
        super();
    }

    public OutOfStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutOfStockException(String message) {
        super(message);
    }

    public OutOfStockException(Throwable cause) {
        super(cause);
    }
}
