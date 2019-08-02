package com.github.shewaeger.magazstore.exceptions;

public class NotFoundException extends ServiceException {
    public NotFoundException(Throwable ex, String message, Object... args) {
        super(ex, message, args);
    }

    public NotFoundException(String message, Object... args) {
        super(message, args);
    }
}
