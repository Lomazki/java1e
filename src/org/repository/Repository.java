package org.repository;

import java.io.IOException;

public interface Repository {

    void readUserBook() throws IOException, ClassNotFoundException;
    void saveToUserBook() throws IOException;

}
