package org.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.validation.ExceptionMessage.EMAIL_INCORRECT;
import static org.validation.ExceptionMessage.NAME_INCORRECT;

public class NameValidate {

    private static final String PATTERN_NAME = "^[A-Za-z]+$";

    public ValidatorError validator(String name) {
        StringBuilder sb = new StringBuilder(name);
        Pattern pattern = Pattern.compile(PATTERN_NAME);
        Matcher matcher = pattern.matcher(sb);

        return (matcher.find()) ? null : new ValidatorError(String.format(NAME_INCORRECT, name));
    }
}
