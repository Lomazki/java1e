package src.org.validation;

import src.org.models.ValidationError;

public interface EmailValidator {

    ValidationError validate(String email);
}
