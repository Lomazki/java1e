package src.org.service;

import src.org.models.User;
import src.org.models.ValidationError;

public interface SearchService {

    ValidationError runSearch();

    User getSearchUser();

}
