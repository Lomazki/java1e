package org.validation;

import org.validation.exception.ValidatorError;

import java.io.IOException;

public interface EmailValidate {

    ValidatorError validatorEmail(String email);
    ValidatorError emailIsExist(String email) throws IOException, ClassNotFoundException;


}
