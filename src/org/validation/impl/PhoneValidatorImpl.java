package org.validation.impl;

import org.validation.PhoneValidator;
import org.models.ValidationError;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.constants.ExceptionMessage.*;

public class PhoneValidatorImpl implements PhoneValidator {

    private static final String PHONE_PATTERN = "^[0-9]{5} [0-9]{7}$";
    private static final String ALLOWED_COUNTER_PHONE = "3";

    public ValidationError validate(List<String> phones) {

        if (phones == null || phones.size() == 0) {
            return new ValidationError(PHONE_LIST_IS_NULL);
        }
        if (phones.size() > 3) {
            return new ValidationError(String.format(INCORRECT_PHONE_NUMBER_COUNT, ALLOWED_COUNTER_PHONE));
        }

        for (String phone : phones) {
            Pattern pattern = Pattern.compile(PHONE_PATTERN);
            Matcher matcher = pattern.matcher(phone.trim());

            if (!matcher.find()) {
                return new ValidationError(String.format(PHONE_INCORRECT, phone));
            }
        }
        return null;
    }
}
