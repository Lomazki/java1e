package org.service.impl;

import org.ScannerWorker;
import org.service.EditService;
import org.models.User;
import org.models.ValidationError;
import org.repository.impl.RepositoryImpl;

import java.io.IOException;
import java.util.List;

import static org.constants.ExceptionMessage.USER_WAS_NOT_EDITED;

public class EditServiceImpl implements EditService {

    private User editedUser;

    private ScannerWorker scannerWorker;
    private RepositoryImpl repository;
    private SearchServiceImpl search;
    private CreateServiceImpl createUser;

    public EditServiceImpl(ScannerWorker scannerWorker, RepositoryImpl repository, SearchServiceImpl search, CreateServiceImpl createUser) {
        this.scannerWorker = scannerWorker;
        this.repository = repository;
        this.search = search;
        this.createUser = createUser;
    }

    public ValidationError runEdit() throws IOException, ClassNotFoundException {

        ValidationError findForEdit = search.runSearch();
        User oldUser;
        if (findForEdit != null) {
            return findForEdit;
        } else {
            oldUser = search.getSearchUser();
        }
        List<User> userList = repository.getUserList();
        User userEdited = edit(search.getSearchUser());
        if (userEdited == null) {
            return new ValidationError(USER_WAS_NOT_EDITED);
        } else {
            userList.add(userEdited);
            userList.remove(oldUser);
            repository.setUserList(userList);
            this.editedUser = userEdited;
            return null;
        }
    }

    public User edit(User user) throws IOException, ClassNotFoundException {

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

    public User getEditedUser() {
        return editedUser;
    }
}