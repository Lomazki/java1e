package org.repository;

import org.models.User;

import java.util.Collection;
import java.util.List;

public interface UserRepository {

    void save(User user);

    User getByEmail(String email);

    Collection<User> getAll();

    void edit(User user);

    boolean delete(User user);
}
