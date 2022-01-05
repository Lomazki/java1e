package org.validation;

import org.validation.exception.ValidatorError;

import java.util.List;

public interface RoleValidate {

    ValidatorError validate(List<String> roles);

}
