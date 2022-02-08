package org.repositories;

import org.models.User;

import java.util.Collection;
import java.util.List;

public interface UserRepository {

    void saveUser(User user);

    void saveAllUsers (List<User> users);

    Collection<User> getAll();

    void edit(User user);

    void delete(User user);

    User getByEmail(String email);

    User getById(long id);
}
