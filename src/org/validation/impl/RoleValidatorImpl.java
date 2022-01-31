package src.org.validation.impl;

import src.org.models.Role;
import src.org.validation.RoleValidator;
import src.org.models.ValidationError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static src.org.constants.ExceptionMessage.*;

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
        Map<Integer, Role> levelToRole = new HashMap<>();
        for (String role : roles) {
            role = role.trim().toUpperCase();
            ValidationError isValidRoleName = isValidRoleName(role);
            if (isValidRoleName != null) {
                return isValidRoleName;
            }

            if ((Role.SUPER_ADMIN.getName().equals(role) && levelToRole.size() > 0
                    || (!Role.SUPER_ADMIN.getName().equals(role)
                    && levelToRole.get(Role.SUPER_ADMIN.getLevel()) != null))) {
                return new ValidationError(SUPER_ADMIN_IS_NOT_SINGLE);
            }

            Role currentRole = Role.valueOf(role);
            int currentLevel = currentRole.getLevel();
            if (levelToRole.get(currentLevel) != null) {
                return new ValidationError(String.format(DOUBLE_LEVEL_ROLE, currentLevel));
            }
            levelToRole.put(currentLevel, currentRole);
        }
        return null;
    }
}
