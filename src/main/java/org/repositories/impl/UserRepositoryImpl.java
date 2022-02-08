package org.repositories.impl;

import org.models.User;
import org.components.DataSource;
import org.repositories.UserRepository;

import java.util.*;

public class UserRepositoryImpl implements UserRepository {

    private static UserRepositoryImpl repository; // почему выдает ошибку при удалении этого поля?
    private final Map<Long, User> idToUser;
    private final DataSource dataSource;

    private UserRepositoryImpl(DataSource dataSource) {
        this.idToUser = new HashMap<>();
        this.dataSource = dataSource;
    }

    @Override
    public void saveUser(User user) {
        if (user != null) {
            idToUser.put(user.getId(), user);
        }
        dataSource.save(new ArrayList<>(this.idToUser.values()));
    }

    @Override
    public void saveAllUsers(List<User> users) {
        dataSource.save(users);
    }

    @Override
    public void edit(User user) {
        idToUser.put(user.getId(), user);
        dataSource.save(new ArrayList<>(idToUser.values()));
    }

    @Override
    public User getByEmail(String email) {
        User result = null;
        for (User user : idToUser.values()) {
            if (user.getEmail().equals(email)) {
                result = user;
            }
        }
        return result;
    }

    @Override
    public User getById(long id) {
        User result = null;
        for (User user : idToUser.values()) {
            if (user.getId() == id) {
                result = user;
            }
        }
        return result;
    }

    @Override
    public void delete(User user) {
        for (User u : idToUser.values()) {
            if (u.getId() == user.getId()) {
                idToUser.remove(user.getId());
                dataSource.save(new ArrayList<>(this.idToUser.values()));
                break;
            }
        }
    }

    @Override
    public Collection<User> getAll() {
        return idToUser.values();
    }

    private void fillInUsers() {
        List<User> users = dataSource.getUsers();
        for (User user : users) {
            idToUser.put(user.getId(), user);
        }
    }

    public static synchronized UserRepositoryImpl getRepository(DataSource dataSource) {
        if (repository == null) {
            repository = new UserRepositoryImpl(dataSource);
            repository.fillInUsers();
            return repository;
        }
        return repository;
    }
}
