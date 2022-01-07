package org.userService.impl;

import org.ScannerWorker;
import org.userService.Search;
import org.models.User;
import org.models.ValidatorError;
import org.repository.impl.RepositoryImpl;
import org.validation.impl.EmailValidatorImpl;

import java.io.IOException;
import java.util.List;

import static org.constants.ExceptionMessage.USER_NOT_FOUND;

public class SearchImpl implements Search {

    User searchUser;
    String email;

    ValidatorError validatorError;
    RepositoryImpl repository = new RepositoryImpl();
    EmailValidatorImpl emailValidate = new EmailValidatorImpl();
    ScannerWorker scannerWorker = new ScannerWorker();

    public ValidatorError findRunning() throws IOException, ClassNotFoundException {

        String email = enterEmail("Enter the user's email");
        search(email);

        if (this.searchUser == null) {
            return new ValidatorError(String.format(USER_NOT_FOUND, email));
        }
        return null;
    }

    public String enterEmail(String message) {

        String email = scannerWorker.newUser(message);
        ValidatorError resultEmailValid = emailValidate.validateEmail(email);
        if (resultEmailValid == null) {
            return email;
        } else {
            do {
                this.validatorError = resultEmailValid;
                System.out.println(this.validatorError.getMessage());
                email = scannerWorker.newUser("Try again");
                this.validatorError = emailValidate.validateEmail(email);
            } while (this.validatorError != null);
        }
        this.email = email;
        return email;
    }

    private void search(String email) throws IOException, ClassNotFoundException {
        List<User> userList = repository.getUserList();
        if (userList == null || userList.size() == 0) {
            repository.readUserBook();
            userList = repository.getUserList();
        }
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                this.searchUser = user;
            }
        }
    }

    public User getSearchUser() {
        return searchUser;
    }

    public String getEmail() {
        return email;
    }
}
