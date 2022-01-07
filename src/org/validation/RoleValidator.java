package org.validation;

import org.models.ValidatorError;

import java.util.List;

public interface RoleValidator {

    ValidatorError validate(List<String> roles);

}
