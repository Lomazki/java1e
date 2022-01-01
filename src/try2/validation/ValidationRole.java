package try2.validation;

import try2.Role;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationRole {

            /*
        Проверяем role на:
        1) Корректность. Есть ли такая роль в enum
        2) Есть ли уже ADMIN
        3) Соответствие уравней (Совместимость ролей)
         */

    public boolean isValidRoleName(String roleName) {
        if (!Role.getRoleNames().contains(roleName.toUpperCase()))
            System.out.println("There is no such role");
        return Role.getRoleNames().contains(roleName.toUpperCase());
    }

    public boolean validate(List<String> roles) {
        Map<Integer, Role> levelToRole = new HashMap<>();
        for (String role : roles) {
            role = role.trim().toUpperCase();
            if (!isValidRoleName(role)) {
                return false;
            }

            if ((Role.SUPER_ADMIN.getName().equals(role) && levelToRole.size() > 0
                    || (!Role.SUPER_ADMIN.getName().equals(role)
                    && levelToRole.get(Role.SUPER_ADMIN.getLevel()) != null))) {
                System.out.println("SUPER_ADMIN already exists");
             return false;
            }

            Role currentRole = Role.valueOf(role);
            int currentLevel = currentRole.level;           // currentLevel     относится к Role
            if (levelToRole.get(currentLevel) != null) {    // levelToRole.get  относится к Map.
                System.out.println("There is already a role of this level");
                return false;
            }
            levelToRole.put(currentLevel, currentRole);
        }
        return true;
    }
}
