package org.userService;

import org.models.ValidatorError;

import java.io.IOException;

public interface Remove {

    ValidatorError removeRunning() throws IOException, ClassNotFoundException;

}
