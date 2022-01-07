package org.validation.impl;

import org.repository.impl.RepositoryImpl;
import org.models.User;
import org.validation.EmailValidator;
import org.models.ValidatorError;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.constants.ExceptionMessage.*;

public class EmailValidatorImpl implements EmailValidator {

    private static final String PATTERN_EMAIL = "^[a-zA-Z0-9.]+@[a-z]+\\.+[a-z]{2,5}$";

    public ValidatorError validateEmail(String email) {
        if (email == null) {
            return new ValidatorError(EMAIL_IS_NULL);
        }
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(email);
        ValidatorError validEmail = new ValidatorError(String.format(EMAIL_INCORRECT, email));
        if (matcher.find()) {
            return null;
        } else {
            return validEmail;
        }
    }

    public ValidatorError isEmailExists (String email) {
        if (email == null) {
            return new ValidatorError(EMAIL_IS_NULL);
        }
        RepositoryImpl repository = new RepositoryImpl();
        List<User> userList = repository.getUserList();
        if (userList == null || userList.isEmpty()) {
            return null;
        }
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                ValidatorError validEmailIsExist = new ValidatorError(String.format(EMAIL_DUPLICATE, email));
                System.out.println(validEmailIsExist.getMessage());
                return validEmailIsExist;
            }
        }
        return null;
    }
}
