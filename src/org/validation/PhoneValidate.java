package org.validation;

import org.validation.exception.ValidatorError;

import java.util.List;

public interface PhoneValidate {

    ValidatorError validator(List<String> phones);

}
