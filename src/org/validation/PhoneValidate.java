package org.validation;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.validation.ExceptionMessage.INCORRECT_PHONE_NUMBER_COUNT;
import static org.validation.ExceptionMessage.PHONE_INCORRECT;

public class PhoneValidate {

    private static final String PHONE_PATTERN = "^[0-9]{5} [0-9]{7}$";

    public ValidatorError validator(List<String> phones) {

        for (String phone : phones) {
            StringBuilder sb = new StringBuilder(phone.trim());
            Pattern pattern = Pattern.compile(PHONE_PATTERN);
            Matcher matcher = pattern.matcher(sb);

            if (!matcher.find()) {
                System.out.println("The phone number is incorrect. Only numbers and one space are allowed. For example, 37576 9851569");
                return new ValidatorError(String.format(PHONE_INCORRECT, phone));
            }
            if (phones.size() > 3) {
                System.out.println("Only 3 phones are allowed");
                return new ValidatorError(INCORRECT_PHONE_NUMBER_COUNT);
            }
        }
        return null;
    }
}
