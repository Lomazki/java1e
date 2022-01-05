package org.validation.impl;

import org.Repository;
import org.User;
import org.validation.EmailValidate;
import org.validation.exception.ValidatorError;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.validation.exception.ExceptionMessage.*;

public class EmailValidateImpl implements EmailValidate {

    private static final String PATTERN_EMAIL = "^[a-zA-Z0-9.]+@[a-z]+\\.+[a-z]{2,5}$";

    @Override
    public ValidatorError validatorEmail(String email) {
        if (email == null) {
            return new ValidatorError(EMAIL_IS_NULL);
        }
        StringBuilder sb = new StringBuilder(email);
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(sb);
        ValidatorError validEmail = new ValidatorError(String.format(EMAIL_INCORRECT, email));
        if (matcher.find()) {
            return null;
        } else {
            System.out.println(validEmail.getMessage());
            return validEmail;
        }
    }

    @Override
    public ValidatorError emailIsExist(String email) throws IOException, ClassNotFoundException {
        if (email == null) {
            return new ValidatorError(EMAIL_IS_NULL);
        }
        Repository repository = new Repository();
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
