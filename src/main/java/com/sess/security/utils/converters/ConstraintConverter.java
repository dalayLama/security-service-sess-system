package com.sess.security.utils.converters;

import javax.validation.ConstraintViolation;
import java.util.function.Function;

public interface ConstraintConverter extends Function<ConstraintViolation<?>, String> {
}
