package src.org;

import src.org.models.User;
import src.org.repository.UserRepository;
import src.org.validation.EmailValidator;
import src.org.validation.NameValidator;
import src.org.validation.PhoneValidator;
import src.org.validation.RoleValidator;

import java.util.Collection;
import java.util.List;

import static src.org.constants.ExceptionMessage.USER_LIST_IS_NULL;

public class Controller {

    private final ConsoleWorker consoleWorker;
    private final UserRepository userRepository;
    private final ScannerWorker scannerWorker;
    private final UserIdGenerator userIdGenerator;
    private final NameValidator nameValidator;
    private final EmailValidator emailValidator;
    private final RoleValidator roleValidator;
    private final PhoneValidator phoneValidator;

    public Controller(ConsoleWorker consoleWorker,
                      UserRepository userRepository,
                      ScannerWorker scannerWorker,
                      UserIdGenerator userIdGenerator,
                      NameValidator nameValidator,
                      EmailValidator emailValidator,
                      RoleValidator roleValidator,
                      PhoneValidator phoneValidator) {
        this.consoleWorker = consoleWorker;
        this.userRepository = userRepository;
        this.scannerWorker = scannerWorker;
        this.userIdGenerator = userIdGenerator;
        this.nameValidator = nameValidator;
        this.emailValidator = emailValidator;
        this.roleValidator = roleValidator;
        this.phoneValidator = phoneValidator;
    }

    void create() {
        long id = userIdGenerator.getId();
        String name = scannerWorker.correctingPress("Please, enter name", nameValidator::validate);
        String lastName = scannerWorker.correctingPress("Please, enter last name", nameValidator::validate);
        String email = scannerWorker.correctingPress("Enter email", emailValidator::validate);
        List<String> role = scannerWorker.correctingListPress(
                "Enter role(s) separated by commas",
                roleValidator::validate);
        List<String> phone = scannerWorker.correctingListPress(
                "Enter phone number(s) separated by commas",
                phoneValidator::validate);

        User newUser = new User(id, name, lastName, email, role, phone);
        userRepository.saveUser(newUser);
        consoleWorker.showUser(newUser);
    }

    void edit() {
        String userEmailForEdit = scannerWorker.typingRequest("Please, enter the email address of the " +
                "person you want to edit");
        User user = userRepository.getByEmail(userEmailForEdit);
        User userEdited = editHelp(user);
        userRepository.edit(userEdited);

        // показать отредактированного юзера
    }

    void remove() {
        String userEmailForDel = scannerWorker.typingRequest("Please, enter the email address of the " +
                "person you want to delete");
        User userForDel = userRepository.getByEmail(userEmailForDel);
        // сделать проверку на "есть ли такой юзер"
        // в идеале сделать валидацию на вводимый email
        userRepository.delete(userForDel);
        // отчет об удалении
    }

    void find() {
        String userEmailForSearch = scannerWorker.typingRequest("Please, enter the email address of the " +
                "person you are looking for");
        User userFound = userRepository.getByEmail(userEmailForSearch);
        // если найден - вывести на консоль. нет - выдать ошибку
        // в идеале сделать валидацию на вводимый email
        System.out.println(userFound);
    }

    void showAll() {
        Collection<User> allUser = userRepository.getAll();
        if (allUser == null || allUser.isEmpty()) {
            consoleWorker.showMessage(USER_LIST_IS_NULL);
        }
        for (User s : allUser) {        /////////////////////////////////////////////
            consoleWorker.showUser(s);
        }
    }

    private User editHelp (User user){
        if (user == null) {
            return null;
        }
        String choice;
        do {
            choice = scannerWorker.editSelect();
        } while ((!(choice.equals("1") ||
                choice.equals("2") ||
                choice.equals("3") ||
                choice.equals("4") ||
                choice.equals("5"))));

        switch (choice) {
            case ("1"): {
                return new User(user.getId(),
                        scannerWorker.correctingPress("Please, enter name", nameValidator::validate),
                        user.getLastName(),
                        user.getEmail(),
                        user.getRoles(),
                        user.getPhoneNumber());
            }
            case ("2"): {
                return new User(user.getId(),
                        user.getFirstName(),
                        scannerWorker.correctingPress("Please, enter last name", nameValidator::validate),
                        user.getEmail(),
                        user.getRoles(),
                        user.getPhoneNumber());
            }
            case ("3"): {
                return new User(user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        scannerWorker.correctingPress("Enter email", emailValidator::validate),
                        user.getRoles(),
                        user.getPhoneNumber());
            }
            case ("4"): {
                return new User(user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        scannerWorker.correctingListPress(
                                "Enter role(s) separated by commas",
                                roleValidator::validate),
                        user.getPhoneNumber());
            }
            case ("5"): {
                return new User(user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getRoles(),
                        scannerWorker.correctingListPress(
                                "Enter phone number(s) separated by commas",
                                phoneValidator::validate));
            }
        }
        return null;
    }
}
