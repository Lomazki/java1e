package org.validation;

import org.models.ValidatorError;

public interface NameValidator {

    ValidatorError validate(String name);
}
