package com.sess.security.core.services.exceptions;

import com.sess.security.exceptions.Error;
import com.sess.security.exceptions.SecurityAppException;

public class CoreServiceException extends SecurityAppException {

    private final Error response;

    public CoreServiceException(Error response) {
        this.response = response;
    }

    public Error getResponse() {
        return response;
    }
}
