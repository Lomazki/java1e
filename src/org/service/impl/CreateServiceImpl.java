package src.org.service.impl;

import src.org.ScannerWorker;
import src.org.repository.UserRepository;
import src.org.service.CreateService;
import src.org.models.User;
import src.org.models.ValidationError;
import src.org.repository.impl.UserRepositoryImpl;
import src.org.validation.EmailValidator;
import src.org.validation.NameValidator;
import src.org.validation.PhoneValidator;
import src.org.validation.RoleValidator;
import src.org.validation.impl.EmailValidatorImpl;
import src.org.validation.impl.NameValidatorImpl;
import src.org.validation.impl.PhoneValidatorImpl;
import src.org.validation.impl.RoleValidatorImpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

public class CreateServiceImpl implements CreateService {

    private User newUser;
    private ValidationError validationError;

    private ScannerWorker scannerWorker;
    // программируем на типе интерфейса
    private UserRepository userRepository;
    private NameValidator nameValidate;
    private EmailValidator emailValidate;
    private RoleValidator roleValidate;
    private PhoneValidator phoneValidate;

    public CreateServiceImpl(ScannerWorker scannerWorker, UserRepositoryImpl repository, NameValidatorImpl nameValidate, EmailValidatorImpl emailValidate, RoleValidatorImpl roleValidate, PhoneValidatorImpl phoneValidate) {
        this.scannerWorker = scannerWorker;
        this.userRepository = repository;
        this.nameValidate = nameValidate;
        this.emailValidate = emailValidate;
        this.roleValidate = roleValidate;
        this.phoneValidate = phoneValidate;
    }

    public ValidationError runCreate() {
        this.newUser = createUser();
        Collection<User> userList = userRepository.getAll();
        userList.add(newUser);
//        userRepository.setUsers(userList);
        return null;
    }

    public User createUser() {

        long id = newId();
        String name = newName("Please, enter name");
        String lastName = newLastName("Please, enter last name");
        String email = newEmail("Enter email");
        List<String> role = newRole("Enter role(s) separated by commas");
        List<String> phone = newPhone("Enter phone number(s) separated by commas");

        return new User(id, name, lastName, email, role, phone);
    }

    private long newId() {

        long idMax = 0;
        Collection<User> users = userRepository.getAll();

        for (User user : users) {
            if (idMax == 0 || user.getId() > idMax) {
                idMax = user.getId();
            }
        }

        return ++idMax;
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

    public String newEmail(String message) {

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
