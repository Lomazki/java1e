package org.validators;

import org.models.ValidationError;

public interface NameValidator {

    ValidationError validate(String name);

}
