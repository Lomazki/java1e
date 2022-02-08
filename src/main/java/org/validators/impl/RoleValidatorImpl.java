package org.validators.impl;

import org.models.Role;
import org.models.ValidationError;
import org.validators.RoleValidator;

import java.util.*;

import static org.constants.ExceptionMessage.*;

public class RoleValidatorImpl implements RoleValidator {

    private ValidationError isValidRoleName(String roleName) {
        return (roleName == null || !Role.getRoleNames().contains(roleName.toUpperCase()))
                ? new ValidationError(String.format(ROLES_ABSENT, roleName, Role.getRoleNames()))
                : null;
    }

    public ValidationError validate(List<String> roles) {
        if (roles == null || roles.size() == 0) {
            return new ValidationError(ROLES_LIST_IS_NULL);
        }

        Set <Integer> roleLevels = new HashSet<>();
        for (String role : roles) {
            role = role.trim().toUpperCase();
            ValidationError isValidRoleName = isValidRoleName(role);

            if (isValidRoleName != null) {
                return isValidRoleName;
            }

            if ((Role.SUPER_ADMIN.getName().equals(role) && roleLevels.size() > 0
                    || (!Role.SUPER_ADMIN.getName().equals(role)
                    && roleLevels.contains(Role.SUPER_ADMIN.getLevel())))) {
                return new ValidationError(SUPER_ADMIN_IS_NOT_SINGLE);
            }

            Role currentRole = Role.valueOf(role);
            Integer currentLevel = currentRole.getLevel();
            if (roleLevels.contains(currentLevel)) {
                return new ValidationError(String.format(DOUBLE_LEVEL_ROLE, currentLevel));
            }
            roleLevels.add(currentLevel);
        }
        return null;
    }
}
