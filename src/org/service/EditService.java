package org.service;

import org.models.User;
import org.models.ValidationError;

import java.io.IOException;

public interface EditService {

    ValidationError runEdit() throws IOException, ClassNotFoundException;

    User edit(User user) throws IOException, ClassNotFoundException;

    User getEditedUser();

}
