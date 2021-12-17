package try2;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class UserService {

    public User create(String name, String lastName, String email, String role, String phone){
        return new User(name, lastName, email, role, phone);
    }

    public User found(List <User> userList, String email){
        for (User user : userList){
            if (user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    public User edit(User user){
        return null;
    }

    public void saveUser (List <User> userList, User newUser) throws IOException {
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
