package org.validation;

import org.models.ValidationError;

public interface NameValidator {

    ValidationError validate(String name);

}
