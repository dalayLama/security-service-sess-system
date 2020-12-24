package com.sess.security.exceptions;

public class SecurityAppException extends RuntimeException {

    public SecurityAppException() {
    }

    public SecurityAppException(String message) {
        super(message);
    }

    public SecurityAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecurityAppException(Throwable cause) {
        super(cause);
    }

    public SecurityAppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
