package com.sess.security.core.services.exceptions;

import com.sess.security.exceptions.Error;
import org.springframework.http.HttpStatus;

public class HttpCoreServiceException extends CoreServiceException {

    private final HttpStatus status;

    public HttpCoreServiceException(HttpStatus status, Error response) {
        super(response);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
