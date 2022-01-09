package org.service.impl;

import org.service.RemoveService;
import org.models.User;
import org.models.ValidationError;
import org.repository.impl.RepositoryImpl;

import java.io.IOException;
import java.util.List;

public class RemoveServiceImpl implements RemoveService {

    private User removedUser;

    private SearchServiceImpl search;
    private RepositoryImpl repository;

    public RemoveServiceImpl(SearchServiceImpl search, RepositoryImpl repository) {
        this.search = search;
        this.repository = repository;
    }

    public ValidationError runRemove() throws IOException, ClassNotFoundException {
        ValidationError searchEmailValid = search.runSearch();
        if (searchEmailValid == null) {
            this.removedUser = search.getSearchUser();
            List<User> resultRemove = remove(repository.getUserList(), removedUser.getEmail());
            if (resultRemove == null) {
                return null;
            }
            repository.setUserList(resultRemove);
            return null;
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

    public User getRemovedUser() {
        return removedUser;
    }
}
