package try2;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    List <User> userList = new ArrayList<>();              // Лист юзеров, который будет храниться в файле

    public void saveUser (User newUser) throws IOException {
        userList.add(newUser);
        Path path = Path.of("resources", "userBook.out");
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            objectOutputStream.writeObject(userList);
        }
    }

    public List readUserBook() throws IOException, ClassNotFoundException {
        Path path = Path.of("resources", "userBook.out");
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path.toFile()))) {
            Object foundUser =  objectInputStream.readObject();
            return (List) foundUser;
        }
    }

}
