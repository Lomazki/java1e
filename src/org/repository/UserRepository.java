package src.org.repository;

import src.org.models.User;

import java.util.Collection;

public interface UserRepository {

    void saveUser(User user);

    User getByEmail(String email);

    Collection<User> getAll();

    boolean edit(User user);

    boolean delete(User user);
}
