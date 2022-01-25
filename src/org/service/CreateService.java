package src.org.service;

import src.org.models.User;
import src.org.models.ValidationError;

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
