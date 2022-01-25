package org.service;

import org.models.User;
import org.models.ValidationError;

import java.io.IOException;

public interface EditService {

    ValidationError runEdit();

    User edit(User user);

    User getEditedUser();

}
