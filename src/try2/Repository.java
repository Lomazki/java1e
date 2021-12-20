package try2;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class Repository {

    public void saveUser (List<User> userList, User newUser) throws IOException {
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
