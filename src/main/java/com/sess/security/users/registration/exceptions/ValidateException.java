package com.sess.security.users.registration.exceptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ValidateException extends RegistrationException {

    private final List<String> errors;

    public ValidateException(Collection<String> errors) {
        this.errors = new ArrayList<>(errors);
    }

    public Collection<String> getErrors() {
        return errors;
    }
}
