package org.validators;

import org.models.ValidationError;

import java.util.List;

public interface PhoneValidator {

    ValidationError validate(List<String> phones);

}
