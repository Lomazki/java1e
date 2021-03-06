package org.validators.impl;

import org.models.ValidationError;
import org.validators.NameValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.constants.ExceptionMessage.*;

public class NameValidatorImpl implements NameValidator {

    private static final String PATTERN_NAME = "^[A-Za-z]+$";

    public ValidationError validate(String name) {
        if (name == null) {
            return new ValidationError(NAME_IS_NULL);
        }
        Pattern pattern = Pattern.compile(PATTERN_NAME);
        Matcher matcher = pattern.matcher(name);

        return (matcher.find())
                ? null
                : new ValidationError(String.format(NAME_INCORRECT, name));
    }
}
