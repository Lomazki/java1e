package org.validators;

import org.models.ValidationError;

import java.util.List;

public interface RoleValidator {

    ValidationError validate(List<String> roles);

}
