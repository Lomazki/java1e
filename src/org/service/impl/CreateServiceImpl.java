package org.service.impl;

import org.ScannerWorker;
import org.service.CreateService;
import org.models.User;
import org.models.ValidationError;
import org.repository.impl.RepositoryImpl;
import org.validation.impl.EmailValidatorImpl;
import org.validation.impl.NameValidatorImpl;
import org.validation.impl.PhoneValidatorImpl;
import org.validation.impl.RoleValidatorImpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CreateServiceImpl implements CreateService {

    private User newUser;
    private ValidationError validationError;

    private ScannerWorker scannerWorker;
    private RepositoryImpl repository;
    private NameValidatorImpl nameValidate;
    private EmailValidatorImpl emailValidate;
    private RoleValidatorImpl roleValidate;
    private PhoneValidatorImpl phoneValidate;

    public CreateServiceImpl(ScannerWorker scannerWorker, RepositoryImpl repository, NameValidatorImpl nameValidate, EmailValidatorImpl emailValidate, RoleValidatorImpl roleValidate, PhoneValidatorImpl phoneValidate) {
        this.scannerWorker = scannerWorker;
        this.repository = repository;
        this.nameValidate = nameValidate;
        this.emailValidate = emailValidate;
        this.roleValidate = roleValidate;
        this.phoneValidate = phoneValidate;
    }

    public ValidationError runCreate() throws IOException, ClassNotFoundException {
        this.newUser = createUser();
        List<User> userList = repository.getUserList();
        userList.add(newUser);
        repository.setUserList(userList);
        return null;
    }

    public User createUser() throws IOException, ClassNotFoundException {

        String name = newName("Please, enter name");
        String lastName = newLastName("Please, enter last name");
        String email = newEmail("Enter email");
        List<String> role = newRole("Enter role(s) separated by commas");
        List<String> phone = newPhone("Enter phone number(s) separated by commas");

        return new User(name, lastName, email, role, phone);
    }

    public String newName(String message) {           // Метод подходит как для создания имени, так и фамилии

        String name = scannerWorker.newUser(message);
        ValidationError nameValid = nameValidate.validate(name);
        if (nameValid == null) {
            return name;
        } else {
            do {
                this.validationError = nameValid;
                System.out.println(this.validationError.getMessage());
                name = scannerWorker.newUser("Try again");
                this.validationError = nameValidate.validate(name);
            } while (this.validationError != null);
        }
        return name;
    }

    public String newLastName(String message) {
        return newName(message);
    }

    public String newEmail(String message) throws IOException, ClassNotFoundException {

        String email = scannerWorker.newUser(message);
        ValidationError emailValid = emailValidate.validate(email);
        if (emailValid == null) {
            return email;
        } else {
            do {
                this.validationError = emailValid;
                System.out.println(this.validationError.getMessage());
                email = scannerWorker.newUser("Try again");
                this.validationError = emailValidate.validate(email);
            } while (this.validationError != null);
        }
        return email;
    }

    public List<String> newRole(String message) {
        String enterRole = scannerWorker.newUser(message);
        Pattern pattern = Pattern.compile(",");
        String[] arrayRole = pattern.split(enterRole.toUpperCase());

        ValidationError roleValid = roleValidate.validate(Arrays.asList(arrayRole));
        if (roleValid == null) {
            return Arrays.asList(arrayRole);
        } else {
            do {
                this.validationError = roleValid;
                System.out.println(this.validationError.getMessage());
                arrayRole = pattern.split(scannerWorker.newUser("Try again").toUpperCase());
                this.validationError = roleValidate.validate(Arrays.asList(arrayRole));
            } while (this.validationError != null);
        }
        return Arrays.asList(arrayRole);
    }

    public List<String> newPhone(String message) {
        String enterPhone = scannerWorker.newUser(message);
        Pattern pattern = Pattern.compile(",");
        String[] arrayPhone = pattern.split(enterPhone.toUpperCase());

        ValidationError phoneValid = phoneValidate.validate(Arrays.asList(arrayPhone));
        if (phoneValid == null) {
            return Arrays.asList(arrayPhone);
        } else {
            do {
                this.validationError = phoneValid;
                System.out.println(this.validationError.getMessage());
                arrayPhone = pattern.split(scannerWorker.newUser("Try again").toUpperCase());
                this.validationError = phoneValidate.validate(Arrays.asList(arrayPhone));
            } while (this.validationError != null);
        }
        return Arrays.asList(arrayPhone);
    }

    public User getNewUser() {
        return newUser;
    }
}
