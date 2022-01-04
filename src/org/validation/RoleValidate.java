package org.validation;

import org.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.validation.ExceptionMessage.*;

public class RoleValidate {

    private ValidatorError isValidRoleName(String roleName) {
        return (Role.getRoleNames().contains(roleName.toUpperCase())) ?
                null : new ValidatorError(String.format(ROLES_ABSENT, roleName));
    }

    public ValidatorError validate(List<String> roles) {
        Map<Integer, Role> levelToRole = new HashMap<>();
        for (String role : roles) {
            role = role.toUpperCase();
            if (isValidRoleName(role)!= null) {
                ValidatorError validatorError = new ValidatorError("ошибка 23 строка");
                System.out.println(validatorError.getMessage());
                return validatorError;
            }

            if ((Role.SUPER_ADMIN.getName().equals(role) && levelToRole.size() > 0
                    || (!Role.SUPER_ADMIN.getName().equals(role)
                    && levelToRole.get(Role.SUPER_ADMIN.getLevel()) != null))) {
                return new ValidatorError(SUPER_ADMIN_IS_NOT_SINGLE);
            }

            Role currentRole = Role.valueOf(role);
            int currentLevel = currentRole.level;
            if (levelToRole.get(currentLevel) != null) {
                return new ValidatorError(String.format(DOUBLE_LEVEL_ROLE, currentLevel));
            }
            levelToRole.put(currentLevel, currentRole);
        }
        return null;
    }
}
