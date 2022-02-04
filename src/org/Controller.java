package src.org;

import src.org.models.Role;
import src.org.models.User;
import src.org.service.UserService;
import src.org.validation.EmailValidator;
import src.org.validation.NameValidator;
import src.org.validation.PhoneValidator;
import src.org.validation.RoleValidator;
import src.org.view.View;

import java.util.*;
import java.util.function.Consumer;

import static src.org.constants.Constants.*;
import static src.org.constants.ExceptionMessage.USER_LIST_IS_NULL;

public class Controller {

    private final ConsoleWorker consoleWorker;
    private final UserService userService;
    private final UserIdGenerator userIdGenerator;
    private final EmailValidator emailValidator;
    private final NameValidator nameValidator;
    private final RoleValidator roleValidator;
    private final PhoneValidator phoneValidator;
    private final View view;

    public Controller(ConsoleWorker consoleWorker,
                      UserService userService,
                      UserIdGenerator userIdGenerator,
                      EmailValidator emailValidator,
                      NameValidator nameValidator,
                      RoleValidator roleValidator,
                      PhoneValidator phoneValidator,
                      View view) {
        this.consoleWorker = consoleWorker;
        this.userService = userService;
        this.userIdGenerator = userIdGenerator;
        this.emailValidator = emailValidator;
        this.nameValidator = nameValidator;
        this.roleValidator = roleValidator;
        this.phoneValidator = phoneValidator;

        this.view = view;
    }

    private static List<String> getAcceptableChoices() {
        return Arrays.asList(ONE, TWO, THREE, FOUR, FIVE);
    }

    private Map<String, Runnable> getMainChoice(){
        Map<String, Runnable> mainActions = new HashMap<>();

        mainActions.put(ONE, this::createUser);
        mainActions.put(TWO, this::findUser);
        mainActions.put(THREE, this::editUser);
        mainActions.put(FOUR, this::removeUser);
        mainActions.put(FIVE, this::showAllUsers);

        return mainActions;
    }

    public void createUser() {
        long id = userIdGenerator.getId();
        String name = consoleWorker.getChoice(ENTER_FIRST_NAME_MESSAGE, nameValidator::validate);
        String lastName = consoleWorker.getChoice(ENTER_LAST_NAME_MESSAGE, nameValidator::validate);
        String email = consoleWorker.getChoice(ENTER_EMAIL_MESSAGE, emailValidator::validate);
        List<Role> role = getRole();
        List<String> phone = consoleWorker
                .getMultiChoice(ENTER_PHONE_NUMBERS_MESSAGE, phoneValidator::validate, COMMA_DELIMITER);

        User newUser = new User(id, name, lastName, email, role, phone);
        userService.save(newUser);
    }

    private List<Role> getRole() {
        List<Role> roles = new ArrayList<>();
        List<String> roleName = consoleWorker
                .getMultiChoice(ENTER_ROLES_MESSAGE, roleValidator::validate, COMMA_DELIMITER);
        for (String role : roleName) {
            roles.add(Role.valueOf(role.toUpperCase()));
        }
        return roles;
    }

    public void editUser() {
        User user = find();

        String choice = consoleWorker.getChoice(getAcceptableChoices(), EDIT_CHOICE_MESSAGE);
        Consumer <User> consumer = getChoiceToEditAction(user).get(choice);
        consumer.accept(user);
    }

    private User find() {
        String email = consoleWorker.getChoice(ENTER_EMAIL_SEARCHABLE_USER_MESSAGE,
                                                emailValidator::validateEmailName);
        return userService.getByEmail(email);
    }

    private Map<String, Consumer<User>> getChoiceToEditAction(User user) {  // Большой вопрос!
        Map<String, Consumer<User>> editAction = new HashMap<>();

        editAction.put(ONE, this::editName);
        editAction.put(TWO, this::editLastName);
        editAction.put(THREE, this::editEmail);
        editAction.put(FOUR, this::editRole);
        editAction.put(FIVE, this::editPhone);
        return editAction;
    }

    private void editName(User user) {
        user.setFirstName(consoleWorker.getChoice(ENTER_FIRST_NAME_MESSAGE, nameValidator::validate));
        userService.save(user);
    }

    private void editLastName(User user) {
        user.setFirstName(consoleWorker.getChoice(ENTER_LAST_NAME_MESSAGE, nameValidator::validate));
        userService.save(user);
    }

    private void editEmail(User user) {
        user.setEmail(consoleWorker.getChoice(ENTER_EMAIL_MESSAGE, emailValidator::validate));
        userService.save(user);
    }

    private void editRole(User user) {
        user.setRole(getRole());
        userService.save(user);
    }

    private void editPhone(User user) {
        user.setPhone(consoleWorker.getMultiChoice(
                ENTER_PHONE_NUMBERS_MESSAGE, phoneValidator::validate, COMMA_DELIMITER));
        userService.save(user);
    }

    public void removeUser() {
        User user = find();
        if (user == null) {
            view.show(USER_WAS_NOT_FOUND);
        } else {
            userService.delete(user);
        }
    }

    public void showAllUsers() {                            // Большой вопрос!
        Collection<User> allUsers = userService.getAll();
        if (allUsers == null || allUsers.isEmpty()) {       // показал сообщение. И что?!?
            view.show(USER_LIST_IS_NULL);
        }
        for (User user : allUsers) {                        // Как избавиться?
            view.show(user);
        }
    }

    public void findUser() {
        User user = find();
        view.showOrDefault(user, USER_WAS_NOT_FOUND);
    }

    public void launch(){
        String choice = consoleWorker.getChoice(getAcceptableChoices(), MAIN_CHOICE);
        Runnable action = getMainChoice().get(choice);
        action.run();
    }
}
