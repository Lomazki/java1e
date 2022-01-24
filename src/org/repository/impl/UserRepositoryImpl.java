package org.repository.impl;

import org.exceptions.UncheckedException;
import org.models.User;
import org.repository.UserRepository;

import java.io.*;
import java.nio.file.Path;
import java.util.*;


//Все методы должны быть безопасные

public class UserRepositoryImpl implements UserRepository {

    private static final String DIRECTORY = "resources";
    private static final String FILE = "userBook.out";

    private static UserRepositoryImpl repository;
    private final Map<Long, User> idToUser;

    private UserRepositoryImpl() {
        idToUser = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    private void fillInUsers() {
        Path path = Path.of(DIRECTORY, FILE);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path.toFile()))) {
            List<User> users = (List<User>) objectInputStream.readObject();

            // сделать код безопасными
            for (User user : users) {
                idToUser.put(user.getId(), user);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new UncheckedException(e);
        }
    }

    @Override
    public void save(User user) {
        if (user != null) {
            idToUser.put(user.getId(), user);
        }

        // подумать над тем, чтобы дозаписать user, а не перезаписывать всех
        Path path = Path.of(DIRECTORY, FILE);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            objectOutputStream.writeObject(new ArrayList<>(this.idToUser.values()));
        } catch (IOException e) {
            throw new UncheckedException(e);
        }
    }

    @Override
    public User getByEmail(String email) {
        User result = null;
        for (User user : idToUser.values()) {
            // определяем пользователя по совпадению email
        }

        return null;
    }

    @Override
    public Collection<User> getAll() {
        return idToUser.values();
    }

    @Override
    public void edit(User user) {
        for (User u : idToUser.values()) {
            // определяем пользователя по совпадению id и удаляем из idToUser + перезаписываем файл + result = true
        }
    }

    @Override
    public boolean delete(User user) {
        boolean result = false;
        for (User u : idToUser.values()) {
            // определяем пользователя по совпадению id и удаляем из idToUser + перезаписываем файл
            idToUser.remove(user.getId());
        }

        return result;
    }

    public static synchronized UserRepositoryImpl getRepository() {
        if (repository == null) {
            repository = newRepository();
        }
        return repository;
    }

    private static UserRepositoryImpl newRepository() {
        UserRepositoryImpl repository = new UserRepositoryImpl();
        repository.fillInUsers();
        return repository;
    }

    public Map<Long, User> getIdToUser() {
        return idToUser;
    }
}
