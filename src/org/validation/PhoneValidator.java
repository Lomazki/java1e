package org.validation;

import org.models.ValidatorError;

import java.util.List;

public interface PhoneValidator {

    ValidatorError validate(List<String> phones);

}
