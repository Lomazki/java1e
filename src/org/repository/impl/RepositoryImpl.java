package org.repository.impl;

import org.models.User;
import org.repository.Repository;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RepositoryImpl implements Repository {

    private List<User> userList;

    public void readUserBook() throws IOException, ClassNotFoundException {
        Path path = Path.of("resources", "userBook.out");
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path.toFile()))) {
            List<User> foundUser = (List<User>) objectInputStream.readObject();
            this.userList = foundUser;
        } catch (FileNotFoundException e) {
            userList = new ArrayList<>();
        }
    }

    public void saveToUserBook() throws IOException {
        Path path = Path.of("resources", "userBook.out");
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            objectOutputStream.writeObject(this.userList);
        }
    }

    public List<User> getUserList() {
        if (userList == null){
            this.userList = new ArrayList<>();
        }
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
