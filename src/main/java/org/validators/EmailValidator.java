package org.validators;

import org.models.ValidationError;

public interface EmailValidator {

    ValidationError validate(String email);
    ValidationError validateEmailName(String email);
}
