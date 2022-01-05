package org.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.validation.ExceptionMessage.*;

public class NameValidate {

    private static final String PATTERN_NAME = "^[A-Za-z]+$";

    public ValidatorError validator(String name) {
        if (name == null){
            return new ValidatorError(NAME_IS_NULL);
        }
        StringBuilder sb = new StringBuilder(name);
        Pattern pattern = Pattern.compile(PATTERN_NAME);
        Matcher matcher = pattern.matcher(sb);

        return (matcher.find()) ? null : new ValidatorError(String.format(NAME_INCORRECT, name));
    }
}
