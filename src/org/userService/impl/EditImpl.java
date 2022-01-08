package org.userService.impl;

import org.ScannerWorker;
import org.userService.Edit;
import org.models.User;
import org.models.ValidatorError;
import org.repository.impl.RepositoryImpl;

import java.io.IOException;
import java.util.List;

import static org.constants.ExceptionMessage.USER_NOT_EDITED;

public class EditImpl implements Edit {

    SearchImpl search = new SearchImpl();
    ScannerWorker scannerWorker = new ScannerWorker();
    CreateImpl createUser = new CreateImpl();

    RepositoryImpl repository = search.repository;      // Уточни про эту запись :)

    public ValidatorError editRunning() throws IOException, ClassNotFoundException {

        ValidatorError findForEdit = search.findRunning();
        if (findForEdit != null) {
            return findForEdit;
        }
        List<User> userList = repository.getUserList();
        User userEdited = edit(search.getSearchUser());
        if (userEdited == null) {
            return new ValidatorError(USER_NOT_EDITED);
        } else {
            userList.add(userEdited);
            repository.setUserList(userList);
            return null;
        }
    }

    public User edit(User user) {

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
                return new User(createUser.newName("Write a new name"),
                        user.getLastName(),
                        user.getEmail(),
                        user.getRole(),
                        user.getPhoneNumber1());
            }
            case ("2"): {
                return new User(user.getFirstName(),
                        createUser.newLastName("Write a new last name"),
                        user.getEmail(),
                        user.getRole(),
                        user.getPhoneNumber1());
            }
            case ("3"): {
                return new User(user.getFirstName(),
                        user.getLastName(),
                        createUser.newEmail("write a new email"),
                        user.getRole(),
                        user.getPhoneNumber1());
            }
            case ("4"): {
                return new User(user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        createUser.newRole("Write a new role(s)"),
                        user.getPhoneNumber1());
            }
            case ("5"): {
                return new User(user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getRole(),
                        createUser.newPhone("Write a new phone number(es)"));
            }
        }
        return null;
    }

}
