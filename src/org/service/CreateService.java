package org.service;

import org.models.User;
import org.models.ValidationError;

import java.io.IOException;
import java.util.List;

public interface CreateService {

    ValidationError runCreate();

    User getNewUser();

    User createUser() ;

    String newName(String message);

    String newLastName(String message);

    String newEmail(String message);

    List<String> newRole(String message);

    List<String> newPhone(String message);

}
