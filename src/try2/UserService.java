package try2;

import java.io.*;
import java.nio.file.Path;

public class UserService {

    public User create(String name, String lastName, String email, String role, String phone){
        return new User(name, lastName, email, role, phone);
    }

    public void saveUser (User user) throws IOException {
        Path path = Path.of("resources", "userBook.out");
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            objectOutputStream.writeObject(user);
        }
    }

    public User readUserBook() throws IOException, ClassNotFoundException {
        Path path = Path.of("resources", "userBook.out");
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path.toFile()))) {
            Object foundUser =  objectInputStream.readObject();
            return (User) foundUser;
        }
    }
}
