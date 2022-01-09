package org.service;

import org.models.User;
import org.models.ValidationError;

import java.io.IOException;

public interface SearchService {

    ValidationError runSearch() throws IOException, ClassNotFoundException;

    User getSearchUser();

}
