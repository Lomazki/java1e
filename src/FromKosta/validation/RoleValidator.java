package FromKosta.validation;


import FromKosta.model.Role;
import FromKosta.model.ValidationError;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static FromKosta.constants.ExceptionMessage.*;
import static FromKosta.model.Role.getRolesAsString;

public class RoleValidator {

  public ValidationError validate(String... roles) {
    return validate(Arrays.asList(roles));
  }

  public ValidationError validate(List<String> roles) {
    if (roles == null || roles.size() == 0) {
      return new ValidationError(String.format(ROLES_ABSENT, getRolesAsString()));
    }

    Map<Integer, Role> levelToRole = new HashMap<>();
    for (String role : roles) {
      if (role == null
          || role.isEmpty()
          || (!isValidRoleName(role))) {

        return new ValidationError(
            String.format(ROLE_IS_INCORRECT, role, getRolesAsString()));
      }

      if (Role.SUPER_ADMIN.getName().equals(role) && levelToRole.size() > 0
          || !Role.SUPER_ADMIN.getName().equals(role)
          && levelToRole.get(Role.SUPER_ADMIN.getLevel()) != null) {

        return new ValidationError(SUPER_ADMIN_IS_NOT_SINGLE);
      }

      Role currentRole = Role.valueOf(role);
      int currentLevel = currentRole.level;
      if (levelToRole.get(currentLevel) != null) {
        return new ValidationError(String.format(DOUBLE_ROLE, role));
      }

      levelToRole.put(currentLevel, currentRole);
    }

    return null;
  }

  private boolean isValidRoleName(String roleName) {
    return Role.getRoleNames().contains(roleName);
  }
}
