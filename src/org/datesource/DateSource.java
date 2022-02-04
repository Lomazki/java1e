package src.org.datesource;

import src.org.models.User;

import java.util.List;

public interface DateSource {

    void save(List<User> users);

    List <User> getUsers();

}
