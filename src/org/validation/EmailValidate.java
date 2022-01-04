package org.validation;

import org.Repository;
import org.User;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.validation.ExceptionMessage.EMAIL_DUPLICATE;
import static org.validation.ExceptionMessage.EMAIL_INCORRECT;

public class EmailValidate {

    private static final String PATTERN_EMAIL = "^[a-zA-Z0-9.]+@[a-z]+\\.+[a-z]{2,5}$";

    public ValidatorError validator(String email) throws IOException, ClassNotFoundException {
        if (validatorEmail(email) != null || emailIsExist(email) != null) {
            return new ValidatorError("");
        }
        return null;
    }

    public ValidatorError validatorEmail(String email) {
        StringBuilder sb = new StringBuilder(email);
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(sb);

        return (matcher.find()) ? null : new ValidatorError(String.format(EMAIL_INCORRECT, email));
    }

    public ValidatorError emailIsExist(String email) throws IOException, ClassNotFoundException {
        Repository repository = new Repository();
        List<User> userList = repository.getUserList();
        for (User user : userList) {
            if (user.getEmail().equals(email)){
                return new ValidatorError(String.format(EMAIL_DUPLICATE, email));
            }
        }
        return null;
    }
}
