package org.repository;

import org.models.User;

import java.util.Collection;

public interface UserRepository {

    void save(User user);

    User getByEmail(String email);

    Collection<User> getAll();

    boolean edit(User user);

    boolean delete(User user);
}
