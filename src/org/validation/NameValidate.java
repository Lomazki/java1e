package org.validation;

import org.validation.exception.ValidatorError;

public interface NameValidate {

    ValidatorError validator(String name);
}
