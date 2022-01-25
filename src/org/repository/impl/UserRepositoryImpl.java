package src.org.repository.impl;

import src.org.exceptions.UncheckedException;
import src.org.models.User;
import src.org.repository.UserRepository;
import src.org.service.EditService;

import java.io.*;
import java.nio.file.Path;
import java.util.*;


//Все методы должны быть безопасные

public class UserRepositoryImpl implements UserRepository {

    private static final String DIRECTORY = "resources";
    private static final String FILE = "userBook.out";

    private static UserRepositoryImpl repository;
    private final Map<Long, User> idToUser;

    private EditService editService;

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
    public boolean edit(User user) {
        boolean isEdited = false;
        User searchUser;
        User editedUser;
        for (User u : idToUser.values()) {
            // определяем пользователя по совпадению id и удаляем из idToUser + перезаписываем файл + result = true
            if (u.getId() == user.getId()) {
                searchUser = u;
                editedUser = editService.edit(searchUser);
                idToUser.remove(searchUser.getId(), searchUser);
                save(editedUser);
                isEdited = true;
                break;
            }
        }
        return isEdited;
    }

    @Override
    public User getByEmail(String email) {
        User result = null;
        for (User user : idToUser.values()) {
            // определяем пользователя по совпадению email
            if (user.getEmail().equals(email)){
                result = user;
            }
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        boolean result = false;
        for (User u : idToUser.values()) {
            // определяем пользователя по совпадению id и удаляем из idToUser + перезаписываем файл
            if (u.getId() == user.getId()) {
                idToUser.remove(user.getId());
                saveFile();
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Collection<User> getAll() {
        return idToUser.values();
    }

    private void saveFile(){
        Path path = Path.of(DIRECTORY, FILE);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            objectOutputStream.writeObject(new ArrayList<>(this.idToUser.values()));
        } catch (IOException e) {
            throw new UncheckedException(e);
        }
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
}
