package org.service.impl;

import org.ScannerWorker;
import org.service.SearchService;
import org.models.User;
import org.models.ValidationError;
import org.repository.impl.UserRepositoryImpl;
import org.validation.impl.EmailValidatorImpl;

import java.util.Collection;

import static org.constants.ExceptionMessage.USER_WAS_NOT_FOUND;

public class SearchServiceImpl implements SearchService {

    private User searchUser;
    private ValidationError validationError;

    private UserRepositoryImpl repository;
    private EmailValidatorImpl emailValidate;
    private ScannerWorker scannerWorker;

    public SearchServiceImpl(UserRepositoryImpl repository, EmailValidatorImpl emailValidate, ScannerWorker scannerWorker) {
        this.repository = repository;
        this.emailValidate = emailValidate;
        this.scannerWorker = scannerWorker;
    }

    public ValidationError runSearch() {

        String email = enterEmail();
        this.searchUser = search(email);

        if (this.searchUser == null) {
            this.validationError = new ValidationError(String.format(USER_WAS_NOT_FOUND, email));
            return this.validationError;
        }
        return null;
    }

    private String enterEmail() {

        String email = scannerWorker.newUser("Enter the user's email");
        ValidationError resultEmailValid = emailValidate.validateNamedEmail(email);
        if (resultEmailValid == null) {
            return email;
        } else {
            do {
                this.validationError = resultEmailValid;
                System.out.println(this.validationError.getMessage());
                email = scannerWorker.newUser("Try again");
                this.validationError = emailValidate.validateNamedEmail(email);
            } while (this.validationError != null);
        }
        return email;
    }

    private User search(String email) {
        Collection<User> userList = repository.getAll();
        if (userList != null) {
            for (User user : userList) {
                if (user.getEmail().equals(email)) {
                    this.searchUser = user;
                    return user;
                }
            }
        }
        return null;
    }

    public User getSearchUser() {
        return searchUser;
    }
}
