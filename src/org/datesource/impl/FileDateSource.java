package src.org.datesource.impl;

import src.org.FileWorker;
import src.org.datesource.DateSource;
import src.org.models.User;

import java.util.List;

public class FileDateSource implements DateSource {

    FileWorker fileWorker;

    public FileDateSource(FileWorker fileWorker) {
        this.fileWorker = fileWorker;
    }

    @Override
    public void save(List<User> users) {
        fileWorker.save(users);
    }

    @Override
    public List<User> getUsers() {
        return fileWorker.read();
    }
}
