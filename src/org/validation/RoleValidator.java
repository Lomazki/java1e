package src.org.validation;

import src.org.models.ValidationError;

import java.util.List;

public interface RoleValidator {

    ValidationError validate(List<String> roles);

}
