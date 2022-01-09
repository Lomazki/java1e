package org.validation.impl;

import org.repository.impl.RepositoryImpl;
import org.models.User;
import org.validation.EmailValidator;
import org.models.ValidationError;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.constants.ExceptionMessage.*;

public class EmailValidatorImpl implements EmailValidator {

    private static final String PATTERN_EMAIL = "^[a-zA-Z0-9.]+@[a-z]+\\.+[a-z]{2,5}$";

    public ValidationError validate(String email) throws IOException, ClassNotFoundException {
        if (validateNamedEmail(email) != null) {
            return validateNamedEmail(email);
        }
        if (isEmailExists(email) != null) {
            return isEmailExists(email);
        } else {
            return null;
        }
    }

    public ValidationError validateNamedEmail(String email) {
        if (email == null) {
            return new ValidationError(EMAIL_IS_NULL);
        }
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(email);
        ValidationError validEmail = new ValidationError(String.format(EMAIL_INCORRECT, email));
        if (matcher.find()) {
            return null;
        } else {
            return validEmail;
        }
    }

    public ValidationError isEmailExists(String email) throws IOException, ClassNotFoundException {
        if (email == null) {
            return new ValidationError(EMAIL_IS_NULL);
        }
        RepositoryImpl repository = RepositoryImpl.getRepository();
        List<User> userList = repository.getUserList();
        if (userList == null || userList.isEmpty()) {
            repository.readUserBook();
            userList = repository.getUserList();
            if (userList == null || userList.isEmpty()) {
                return null;
            }
        }
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return new ValidationError(String.format(EMAIL_DUPLICATE, email));
            }
        }
        return null;
    }
}
