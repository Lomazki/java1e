package org.validators.impl;

import org.models.User;
import org.models.ValidationError;
import org.services.UserService;
import org.validators.EmailValidator;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.constants.ExceptionMessage.*;

public class EmailValidatorImpl implements EmailValidator {

    private static final String PATTERN_EMAIL = "^[a-zA-Z0-9.]+@[a-z]+\\.+[a-z]{2,5}$";
    private final UserService userService;

    public EmailValidatorImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ValidationError validate(String email) {
        if (email == null) {
            return new ValidationError(EMAIL_IS_NULL);
        }

        ValidationError validationError = validateEmailName(email);
        if (validationError != null) {
            return validationError;
        }
        return validateUniq(email);
    }

    @Override
    public ValidationError validateEmailName(String email) {

        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(email);

        return matcher.find()
                ? null
                : new ValidationError(String.format(EMAIL_INCORRECT, email));
    }

    private ValidationError validateUniq(String email) {
        Collection<User> users = userService.getAll();
        if (users == null || users.isEmpty()) {
            return null;
        }

        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return new ValidationError(String.format(EMAIL_DUPLICATE, email));
            }
        }
        return null;
    }
}
