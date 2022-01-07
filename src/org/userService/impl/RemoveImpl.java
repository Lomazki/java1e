package org.userService.impl;

import org.userService.Remove;
import org.models.User;
import org.models.ValidatorError;
import org.repository.impl.RepositoryImpl;

import java.io.IOException;
import java.util.List;

import static org.constants.ExceptionMessage.USER_WAS_REMOVED;

public class RemoveImpl implements Remove {

    SearchImpl search = new SearchImpl();
    RepositoryImpl repository = search.repository;                  // Спроси про эту запись!

    List <User> newUserList;

    public ValidatorError removeRunning() throws IOException, ClassNotFoundException {
        ValidatorError searchEmailValid = search.findRunning();
        if (searchEmailValid == null) {
            repository.setUserList(remove(repository.getUserList(), search.getEmail()));
            this.newUserList = repository.getUserList();
            return new ValidatorError(USER_WAS_REMOVED);
        } else {
            return searchEmailValid;
        }
    }

    private List<User> remove(List<User> userList, String email) {
        if (userList == null || userList.size() == 0) {
            return null;
        }
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                userList.remove(user);
                return userList;
            }
        }
        return null;
    }

    public List<User> getNewUserList() {
        return newUserList;
    }
}
