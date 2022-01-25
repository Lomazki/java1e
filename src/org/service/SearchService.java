package org.service;

import org.models.User;
import org.models.ValidationError;

public interface SearchService {

    ValidationError runSearch();

    User getSearchUser();

}
