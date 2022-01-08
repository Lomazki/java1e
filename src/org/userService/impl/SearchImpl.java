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

        String email = enterEmail();
        this.searchUser = search(email);

        if (this.searchUser == null) {
            this.validatorError = new ValidatorError(String.format(USER_NOT_FOUND, email));
            return this.validatorError;
        }
        return null;
    }

    private String enterEmail() {

        String email = scannerWorker.newUser("Enter the user's email");
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

    private User search(String email) throws IOException, ClassNotFoundException {
        List<User> userList = repository.getUserList();
        if (userList == null || userList.size() == 0) {
            repository.readUserBook();
            userList = repository.getUserList();
        }
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                this.searchUser = user;
                return user;
            }
        }
        return null;
    }

    public User getSearchUser() {
        return searchUser;
    }

    public String getEmail() {
        return email;
    }
}
