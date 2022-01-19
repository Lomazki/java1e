package org.validation;

import org.models.ValidationError;

import java.io.IOException;

public interface EmailValidator {

    ValidationError validateNamedEmail(String email);

    ValidationError isEmailExists(String email) throws IOException, ClassNotFoundException;

}
