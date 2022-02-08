package org.components;

import org.models.User;

import java.util.List;

public interface DataSource {

    void save(List<User> users);

    List <User> getUsers();

}
