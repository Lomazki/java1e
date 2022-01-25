package src.org.service;

import src.org.models.User;

import java.util.Collection;

public interface UserService {

    void save(User user);

    User getByEmail(String email);

    Collection<User> getAll();

    void edit(User user);

    boolean delete(User user);
}
