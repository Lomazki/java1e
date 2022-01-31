package src.org.repository.impl;

import src.org.FileWorker;
import src.org.models.User;
import src.org.repository.UserRepository;

import java.util.*;

//Все методы должны быть безопасные
public class UserRepositoryImpl implements UserRepository {

    private static UserRepositoryImpl repository;
    private final Map<Long, User> idToUser;
    private final FileWorker fileWorker;

    private UserRepositoryImpl(FileWorker fileWorker) {
        this.idToUser = new HashMap<>();
        this.fileWorker = fileWorker;
    }

    @Override
    public void saveUser(User user) {
        if (user != null) {
            idToUser.put(user.getId(), user);
        }
        fileWorker.save(new ArrayList<>(this.idToUser.values()));
    }

    @Override
    public boolean edit(User user) { //////////////////////////////////////////
        boolean isEdited = false;
//        User searchUser;
//        User editedUser;
        idToUser.put(user.getId(), user);
        fileWorker.save(new ArrayList<>(idToUser.values()));
//        for (User u : idToUser.values()) {
//            // определяем пользователя по совпадению id и удаляем из idToUser + перезаписываем файл + result = true
//            if (u.getId() == user.getId()) {
//                searchUser = u;
//                editedUser = editHelper.edit(searchUser);
//                idToUser.remove(searchUser.getId(), searchUser);
//                saveUser(editedUser);
//                isEdited = true;
//                break;
//            }
//        }
        return isEdited;
    }

    @Override
    public User getByEmail(String email) {
        User result = null;
        for (User user : idToUser.values()) {
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
            if (u.getId() == user.getId()) {
                idToUser.remove(user.getId());
                fileWorker.save(new ArrayList<>(this.idToUser.values()));
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

    private void fillInUsers() {
        List<User> users = fileWorker.read();
        for (User user : users) {
            idToUser.put(user.getId(), user);
        }
    }

    public static synchronized UserRepositoryImpl getRepository() {
        if (repository == null) {
            repository = newRepository();
        }
        return repository;
    }

    private static UserRepositoryImpl newRepository() {
        UserRepositoryImpl repository = new UserRepositoryImpl(new FileWorker());
        repository.fillInUsers();
        return repository;
    }
}
