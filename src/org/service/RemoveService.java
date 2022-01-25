package src.org.service;

import src.org.models.User;
import src.org.models.ValidationError;

public interface RemoveService {

    ValidationError runRemove();

    User getRemovedUser();

}
