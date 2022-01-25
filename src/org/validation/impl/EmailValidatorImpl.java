package src.org.validation.impl;

import src.org.repository.impl.UserRepositoryImpl;
import src.org.models.User;
import src.org.validation.EmailValidator;
import src.org.models.ValidationError;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static src.org.constants.ExceptionMessage.*;

public class EmailValidatorImpl implements EmailValidator {

    private static final String PATTERN_EMAIL = "^[a-zA-Z0-9.]+@[a-z]+\\.+[a-z]{2,5}$";

    public ValidationError validate(String email) {
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

    public ValidationError isEmailExists(String email) {
        if (email == null) {
            return new ValidationError(EMAIL_IS_NULL);
        }
        UserRepositoryImpl repository = UserRepositoryImpl.getRepository();
        Collection<User> userList = repository.getAll();
        if (userList == null || userList.isEmpty()) {
            userList = repository.getAll();
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
