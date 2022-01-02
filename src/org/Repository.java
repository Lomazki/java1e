package org;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    List<User> userList;

    public Repository() throws IOException, ClassNotFoundException {
        this.userList = readUserBook();
    }

    public void saveUser(User newUser) throws IOException, ClassNotFoundException {
        if (readUserBook() == null) {
            userList = new ArrayList<>();
            userList.add(newUser);
        } else userList.add(newUser);
        Path path = Path.of("resources", "userBook.txt");
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            objectOutputStream.writeObject(userList);
        }
    }

    public List readUserBook() throws IOException, ClassNotFoundException {
        var path = Path.of("resources", "userBook.txt");
        try (var objectInputStream = new ObjectInputStream(new FileInputStream(path.toFile()))) {
            var foundUser = objectInputStream.readObject();
            return (List) foundUser;
        }
    }

    public void saveList(List<User> list) throws IOException {
        Path path = Path.of("resources", "userBook.txt");
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            objectOutputStream.writeObject(list);
        }
    }
}