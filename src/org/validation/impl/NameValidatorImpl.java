package org.validation.impl;

import org.validation.NameValidator;
import org.models.ValidatorError;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.constants.ExceptionMessage.*;

public class NameValidatorImpl implements NameValidator {

    private static final String PATTERN_NAME = "^[A-Za-z]+$";

    @Override
    public ValidatorError validate(String name) {
        if (name == null) {
            return new ValidatorError(NAME_IS_NULL);
        }
        Pattern pattern = Pattern.compile(PATTERN_NAME);
        Matcher matcher = pattern.matcher(name);

        return (matcher.find()) ? null : new ValidatorError(String.format(NAME_INCORRECT, name));
    }
}
