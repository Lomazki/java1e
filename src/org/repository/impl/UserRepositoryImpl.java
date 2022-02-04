package src.org.repository.impl;

import src.org.datesource.DateSource;
import src.org.models.User;
import src.org.repository.UserRepository;

import java.util.*;

//Все методы должны быть безопасные
public class UserRepositoryImpl implements UserRepository {

    private static UserRepositoryImpl repository;
    private final Map<Long, User> idToUser;
    private final DateSource dateSource;

    private UserRepositoryImpl(DateSource dateSource) {
        this.idToUser = new HashMap<>();
        this.dateSource = dateSource;
    }

    @Override
    public void saveUser(User user) {
        if (user != null) {
            idToUser.put(user.getId(), user);
        }
        dateSource.save(new ArrayList<>(this.idToUser.values()));
    }

    @Override
    public void saveAllUsers(List<User> users) {
        dateSource.save(users);
    }

    @Override
    public void edit(User user) {
        idToUser.put(user.getId(), user);
        dateSource.save(new ArrayList<>(idToUser.values()));
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
                dateSource.save(new ArrayList<>(this.idToUser.values()));
                break;
            }
        }
    }

    @Override
    public Collection<User> getAll() {
        return idToUser.values();
    }

    private void fillInUsers() {
        List<User> users = dateSource.getUsers();
        for (User user : users) {
            idToUser.put(user.getId(), user);
        }
    }

    public static synchronized UserRepositoryImpl getRepository(DateSource dateSource) {
        if (repository == null) {
            UserRepositoryImpl repository = new UserRepositoryImpl(dateSource);
            repository.fillInUsers();
            return repository;
        }
        return repository;
    }
}
