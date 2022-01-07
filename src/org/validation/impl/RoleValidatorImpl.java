package org.validation.impl;

import org.models.Role;
import org.validation.RoleValidator;
import org.models.ValidatorError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.constants.ExceptionMessage.*;

public class RoleValidatorImpl implements RoleValidator {

    private ValidatorError isValidRoleName(String roleName) {
        return (roleName == null || !Role.getRoleNames().contains(roleName.toUpperCase())) ?
                new ValidatorError(String.format(ROLES_ABSENT, roleName, Role.getRoleNames())) : null;
    }

    @Override
    public ValidatorError validate(List<String> roles) {
        if (roles == null || roles.size() == 0) {
            return new ValidatorError(ROLES_LIST_IS_NULL);
        }
        Map<Integer, Role> levelToRole = new HashMap<>();
        for (String role : roles) {
            role = role.trim().toUpperCase();
            ValidatorError isValidRoleName = isValidRoleName(role);
            if (isValidRoleName != null) {
                System.out.println(isValidRoleName.getMessage());
                return isValidRoleName;
            }

            if ((Role.SUPER_ADMIN.getName().equals(role) && levelToRole.size() > 0
                    || (!Role.SUPER_ADMIN.getName().equals(role)
                    && levelToRole.get(Role.SUPER_ADMIN.getLevel()) != null))) {
                return new ValidatorError(SUPER_ADMIN_IS_NOT_SINGLE);
            }

            Role currentRole = Role.valueOf(role);
            int currentLevel = currentRole.getLevel();
            if (levelToRole.get(currentLevel) != null) {
                return new ValidatorError(String.format(DOUBLE_LEVEL_ROLE, currentLevel));
            }
            levelToRole.put(currentLevel, currentRole);
        }
        return null;
    }
}
