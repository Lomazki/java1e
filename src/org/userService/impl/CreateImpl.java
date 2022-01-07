package org.userService.impl;

import org.ScannerWorker;
import org.userService.Create;
import org.models.User;
import org.models.ValidatorError;
import org.repository.impl.RepositoryImpl;
import org.validation.impl.EmailValidatorImpl;
import org.validation.impl.NameValidatorImpl;
import org.validation.impl.PhoneValidatorImpl;
import org.validation.impl.RoleValidatorImpl;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.constants.ExceptionMessage.USER_LIST_IS_NULL;

public class CreateImpl implements Create {

    NameValidatorImpl nameValidate = new NameValidatorImpl();
    EmailValidatorImpl emailValidate = new EmailValidatorImpl();
    RoleValidatorImpl roleValidate = new RoleValidatorImpl();
    PhoneValidatorImpl phoneValidate = new PhoneValidatorImpl();
    RepositoryImpl repository = new RepositoryImpl();
    ScannerWorker scannerWorker = new ScannerWorker();


    public ValidatorError runningCreate(){
        User newUser = createNewUser();
        List<User> userList = repository.getUserList();
        if (userList == null || userList.size()==0){
            return new ValidatorError(USER_LIST_IS_NULL);
        }
        userList.add(newUser);
        repository.setUserList(userList);
        return null;
    }

    public User createNewUser() {

        String name = newName("Please, enter name");
        String lastName = newLastName("Please, enter last name");
        String email = newEmail("Enter email");
        List<String> role = newRole("Enter role(s) separated by commas");
        List<String> phone = newPhone("Enter phone number(s) separated by commas");

        return new User(name, lastName, email, role, phone);
    }


    public String newName(String message) {           // Метод подходит как для создания имени, так и фамилии

        String name = scannerWorker.newUser(message);
        if (nameValidate.validate(name) == null) {
            return name;
        } else {
            do {
                name = scannerWorker.newUser("Only letters of the Latin alphabet are allowed");
            } while ((nameValidate.validate(name)) != null);
        }
        return name;
    }

    public String newLastName(String message){
        return newName(message);
    }

    public String newEmail(String message) {

        String email = scannerWorker.newUser(message);
        if (emailValidate.validateEmail(email) == null && emailValidate.isEmailExists(email) == null) {
            return email;
        } else {
            do {
                email = scannerWorker.newUser("Try again");
            } while (emailValidate.validateEmail(email) != null || emailValidate.isEmailExists(email) != null);
        }
        return email;
    }

    public List<String> newRole(String message) {
        List<String> roleList;
        String enterRole = scannerWorker.newUser(message);
        Pattern pattern = Pattern.compile(",");
        String[] array = pattern.split(enterRole.trim().toUpperCase());
        roleList = Arrays.asList(array);

        if ((roleValidate.validate(roleList)) == null) {
            return roleList;
        } else {
            do {
                enterRole = scannerWorker.newUser("Try again");
                roleList = Arrays.asList(pattern.split(enterRole.trim().toUpperCase()));
            } while ((roleValidate.validate(roleList)) != null);
        }
        return roleList;
    }

    public List<String> newPhone(String message) {
        String enterPhone = scannerWorker.newUser(message);
        Pattern pattern = Pattern.compile(",");
        String[] arrayPhone = pattern.split(enterPhone.trim());
        if ((phoneValidate.validate(Arrays.asList(arrayPhone))) == null) {
            return Arrays.asList(arrayPhone);
        } else {
            do {
                enterPhone = scannerWorker.newUser("Try again");
                arrayPhone = (pattern.split(enterPhone));
            } while ((phoneValidate.validate(Arrays.asList(arrayPhone))) != null);
        }
        return Arrays.asList(arrayPhone);
    }

}
