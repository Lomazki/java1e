package org.service.impl;

import org.service.ShowAllService;
import org.models.User;
import org.models.ValidationError;
import org.repository.impl.RepositoryImpl;

import java.io.IOException;
import java.util.List;

import static org.constants.ExceptionMessage.USER_LIST_IS_NULL;

public class ShowAllServiceImpl implements ShowAllService {


    private RepositoryImpl repository;

    public ShowAllServiceImpl(RepositoryImpl repository) {
        this.repository = repository;
    }

    public ValidationError showAllUser() throws IOException, ClassNotFoundException {
        repository.readUserBook();
        List<User> allUser = repository.getUserList();
        if (allUser == null || allUser.isEmpty()) {
            repository.readUserBook();
            return new ValidationError(USER_LIST_IS_NULL);
        }
        for (User s : allUser) {
            System.out.println(s);
        }
        return null;
    }
}
