package org.service;

import org.models.ValidationError;

import java.io.IOException;

public interface ShowAllService {

    ValidationError showAllUser() throws IOException, ClassNotFoundException;

}
