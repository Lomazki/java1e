package org;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    private List<User> userList;

    public Repository() throws IOException, ClassNotFoundException {
        this.userList = readUserBook();
    }

    private List<User> readUserBook() throws IOException, ClassNotFoundException {
        Path path = Path.of("resources", "userBook.out");
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path.toFile()))) {
            List<User> foundUser = (List<User>) objectInputStream.readObject();
            return foundUser;
        } catch (FileNotFoundException e) {
            userList = new ArrayList<>();
            return userList;
        }
    }

    public void saveList(List<User> list) throws IOException {
        Path path = Path.of("resources", "userBook.out");
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            objectOutputStream.writeObject(list);
        }
    }

    public List<User> getUserList() {
        return userList;
    }
}
