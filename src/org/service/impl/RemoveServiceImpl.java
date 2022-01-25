package src.org.service.impl;

import src.org.service.RemoveService;
import src.org.models.User;
import src.org.models.ValidationError;
import src.org.repository.impl.UserRepositoryImpl;

import java.util.Collection;

public class RemoveServiceImpl implements RemoveService {

    private User removedUser;

    private SearchServiceImpl search;
    private UserRepositoryImpl repository;

    public RemoveServiceImpl(SearchServiceImpl search, UserRepositoryImpl repository) {
        this.search = search;
        this.repository = repository;
    }

    public ValidationError runRemove() {
        ValidationError searchEmailValid = search.runSearch();
        if (searchEmailValid == null) {
            this.removedUser = search.getSearchUser();
            Collection<User> resultRemove = remove(repository.getAll(), removedUser.getEmail());
            if (resultRemove == null) {
                return null;
            }
//            repository.setUsers(resultRemove);
            return null;
        } else {
            return searchEmailValid;
        }
    }

    private Collection<User> remove(Collection<User> userList, String email) {
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
