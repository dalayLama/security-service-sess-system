package com.sess.security.users.registration.exceptions;

import com.sess.security.exceptions.Error;
import com.sess.security.core.services.exceptions.CoreServiceException;
import com.sess.security.exceptions.OperationAppException;

import java.util.Objects;

public class RegistrationException extends OperationAppException {

    private final CoreServiceException coreException;

    public RegistrationException(Error error) {
        super(error);
        this.coreException = null;
    }

    public RegistrationException(String message, Error error) {
        super(message, error);
        this.coreException = null;
    }

    public RegistrationException(String message, CoreServiceException cause, Error error) {
        super(message, cause, error);
        this.coreException = cause;
    }

    public RegistrationException(CoreServiceException cause, Error error) {
        super(cause, error);
        this.coreException = cause;
    }

    public boolean hasCoreException() {
        return Objects.nonNull(coreException);
    }

    public CoreServiceException getCoreException() {
        return coreException;
    }
}
