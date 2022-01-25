package src.org.service;

import src.org.models.User;
import src.org.models.ValidationError;

import java.io.IOException;

public interface EditService {

    ValidationError runEdit();

    User edit(User user);

    User getEditedUser();

}
