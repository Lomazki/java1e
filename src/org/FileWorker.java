package src.org;

import src.org.exceptions.UncheckedException;
import src.org.models.User;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileWorker {

    private static final String DIRECTORY = "resources";
    private static final String FILE = "userBook.out";

    @SuppressWarnings("unchecked")
    public List<User> read() {
        Path path = Path.of(DIRECTORY, FILE);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path.toFile()))) {
            return (List<User>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new UncheckedException(e);
        }
    }

    public void save(List<User> users) {
        Path path = Path.of(DIRECTORY, FILE);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            objectOutputStream.writeObject(users);
        } catch (IOException e) {
            throw new UncheckedException(e);
        }
    }
}
