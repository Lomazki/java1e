package src.org.validation;

import src.org.models.ValidationError;

public interface NameValidator {

    ValidationError validate(String name);

}
