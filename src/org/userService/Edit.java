package org.userService;

import org.models.User;
import org.models.ValidatorError;

import java.io.IOException;

public interface Edit {

    ValidatorError editRunning() throws IOException, ClassNotFoundException;
    User edit(User user);

}
