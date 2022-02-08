package org.controllers;

import org.utils.UserIdGenerator;
import org.components.InteractiveWorker;
import org.models.Role;
import org.models.User;
import org.services.UserService;
import org.validators.EmailValidator;
import org.validators.NameValidator;
import org.validators.PhoneValidator;
import org.validators.RoleValidator;
import org.views.View;

import java.util.*;
import java.util.function.Consumer;

import static org.constants.Constants.*;
import static org.constants.ExceptionMessage.USER_LIST_IS_NULL;

public class Controller {

    private final InteractiveWorker consoleWorker;
    private final UserService userService;
    private final UserIdGenerator userIdGenerator;
    private final EmailValidator emailValidator;
    private final NameValidator nameValidator;
    private final RoleValidator roleValidator;
    private final PhoneValidator phoneValidator;
    private final View view;

    public Controller(InteractiveWorker consoleWorker,
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

    private List<String> getAcceptableChoices() {
        return Arrays.asList(ONE, TWO, THREE, FOUR, FIVE);
    }

    private Map<String, Runnable> getMainChoice() {
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
        Consumer<User> consumer = getChoiceToEditAction().get(choice);
        consumer.accept(user);
    }

    private User find() {
        String email = consoleWorker.getChoice(ENTER_EMAIL_SEARCHABLE_USER_MESSAGE,
                emailValidator::validateEmailName);
        return userService.getByEmail(email);
    }

    private Map<String, Consumer<User>> getChoiceToEditAction() {  // Большой вопрос!
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
        user.setLastName(consoleWorker.getChoice(ENTER_LAST_NAME_MESSAGE, nameValidator::validate));
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

    public void showAllUsers() {
        Collection<User> allUsers = userService.getAll();
        if (allUsers == null || allUsers.isEmpty()) {
            view.show(USER_LIST_IS_NULL);
        } else {
            for (User user : allUsers) {
                view.show(user);
            }
        }
    }

    public void findUser() {
        User user = find();
        view.showOrDefault(user, USER_WAS_NOT_FOUND);
    }

    public void launch() {
        String choice = consoleWorker.getChoice(getAcceptableChoices(), MAIN_CHOICE);
        Runnable action = getMainChoice().get(choice);
        action.run();
    }
}
