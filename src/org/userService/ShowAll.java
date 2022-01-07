package org.userService;

import org.models.ValidatorError;

import java.io.IOException;

public interface ShowAll {

    ValidatorError showAllUser () throws IOException, ClassNotFoundException;

}
