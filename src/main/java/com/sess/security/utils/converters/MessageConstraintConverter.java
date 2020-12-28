package com.sess.security.utils.converters;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;

@Component
public class MessageConstraintConverter implements ConstraintConverter {

    @Override
    public String apply(ConstraintViolation<?> constraintViolation) {
        return constraintViolation.getMessage();
    }

}
