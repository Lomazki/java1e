package org.service;

import org.models.User;
import org.models.ValidationError;

import java.io.IOException;

public interface RemoveService {

    ValidationError runRemove();

    User getRemovedUser();

}
