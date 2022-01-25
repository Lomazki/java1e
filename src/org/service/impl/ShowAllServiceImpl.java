package src.org.service.impl;

import src.org.service.ShowAllService;
import src.org.models.User;
import src.org.models.ValidationError;
import src.org.repository.impl.UserRepositoryImpl;

import java.util.Collection;

import static src.org.constants.ExceptionMessage.USER_LIST_IS_NULL;

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
