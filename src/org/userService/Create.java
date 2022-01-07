package org.userService;

import org.models.User;
import org.models.ValidatorError;

import java.util.List;

public interface Create {

    ValidatorError runningCreate();
    User createNewUser();
    String newName(String message);
    String newLastName(String message);
    List<String> newRole(String message);
    List<String> newPhone(String message);

}
