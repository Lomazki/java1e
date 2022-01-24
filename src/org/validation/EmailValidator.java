package org.validation;

import org.models.ValidationError;

public interface EmailValidator {

    ValidationError validate(String email);

    ValidationError validateNamedEmail(String email);

    ValidationError isEmailExists(String email);

}
