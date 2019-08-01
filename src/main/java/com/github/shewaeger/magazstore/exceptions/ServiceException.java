package com.github.shewaeger.magazstore.exceptions;

public class ServiceException extends RuntimeException {
    public ServiceException(String message, Object... args) {
        super(String.format(message, args));
    }

    public ServiceException(Throwable ex, String message, Object... args) {
        super(String.format(message, args), ex);
    }

    public ServiceException(Throwable ex) {
        super(ex);
    }
}
