package org.service.impl;

import org.service.ShowAllService;
import org.models.User;
import org.models.ValidationError;
import org.repository.impl.UserRepositoryImpl;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static org.constants.ExceptionMessage.USER_LIST_IS_NULL;

public class ShowAllServiceImpl implements ShowAllService {


    private UserRepositoryImpl repository;

    public ShowAllServiceImpl(UserRepositoryImpl repository) {
        this.repository = repository;
    }

    public ValidationError showAllUser() {
        Collection<User> allUser = repository.getAll();
        if (allUser == null || allUser.isEmpty()) {
            return new ValidationError(USER_LIST_IS_NULL);
        }
        for (User s : allUser) {
            System.out.println(s);
        }
        return null;
    }
}
