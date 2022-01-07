package org.userService.impl;

import org.userService.ShowAll;
import org.models.User;
import org.models.ValidatorError;
import org.repository.impl.RepositoryImpl;

import java.io.IOException;
import java.util.List;

import static org.constants.ExceptionMessage.USER_LIST_IS_NULL;

public class ShowAllImpl implements ShowAll {


    RepositoryImpl repository = new RepositoryImpl();

    public ValidatorError showAllUser () throws IOException, ClassNotFoundException {
        List<User> allUser = repository.getUserList();
        if (allUser == null || allUser.isEmpty()) {
            repository.readUserBook();
            return new ValidatorError(USER_LIST_IS_NULL);
        }
        for (User s : allUser) {
            System.out.println(s);
        }
        return null;
    }

}
