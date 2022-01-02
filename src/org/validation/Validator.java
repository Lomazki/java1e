package org.validation;

import org.Repository;
import org.User;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public Boolean validatorName(String name) {
        StringBuilder sb = new StringBuilder(name);
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        Matcher matcher = pattern.matcher(sb);
        return matcher.find();
    }

    public Boolean validatorEmail(String email) {
        StringBuilder sb = new StringBuilder(email);
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9.]+@[a-z]+\\.+[a-z]{2,5}$");
        Matcher matcher = pattern.matcher(sb);
        return matcher.find();
    }

    public boolean emailExist(String email) throws IOException, ClassNotFoundException {
        var repository = new Repository();
        if (repository.readUserBook() == null) return false;
        for (Object userObject : repository.readUserBook()) {
            User user = (User) userObject;
            if (user.getEmail().equals(email)) {
                System.out.println("The email already exists");
                return true;
            }
        }
        return false;
    }
}
