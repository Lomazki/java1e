package org.components.impl;

import org.components.DataSource;
import org.serializers.FileWorker;
import org.models.User;

import java.util.List;

public class FileDataSource implements DataSource {

    FileWorker fileWorker;

    public FileDataSource(FileWorker fileWorker) {
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
