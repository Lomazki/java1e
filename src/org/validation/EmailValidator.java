package org.validation;

import org.models.ValidatorError;

import java.io.IOException;

public interface EmailValidator {

    ValidatorError validateEmail(String email);

    ValidatorError isEmailExists(String email) throws IOException, ClassNotFoundException;

}
