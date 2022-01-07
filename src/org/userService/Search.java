package org.userService;

import org.models.ValidatorError;

import java.io.IOException;

public interface Search {

    ValidatorError findRunning() throws IOException, ClassNotFoundException;

}
