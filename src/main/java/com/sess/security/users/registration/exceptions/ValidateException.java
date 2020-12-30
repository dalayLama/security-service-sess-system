package com.sess.security.users.registration.exceptions;

import com.sess.security.exceptions.Error;
import com.sess.security.exceptions.ErrorBuilder;
import com.sess.security.utils.ErrorsCodes;

import java.util.Collection;

public class ValidateException extends RegistrationException {

    public ValidateException(Collection<String> errors) {
        super(ErrorBuilder.listMessages(false, ErrorsCodes.VALIDATE_ERROR, errors));
    }

    public ValidateException(Error error) {
        super(error);
    }

}
