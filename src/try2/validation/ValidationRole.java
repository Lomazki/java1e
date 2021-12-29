package try2.validation;

import try2.Role;

public class ValidationRole {

            /*
        Проверяем role на:
        1) Корректность. Есть ли такая роль в enum
        2) Есть ли уже ADMIN
        3) Соответствие уравней (Совместимость ролей)
         */

    public boolean isValidRoleName (String roleName){
        if (!Role.getRoleNames().contains(roleName.toUpperCase()))
            System.out.println("Такой роли не существует");
        return Role.getRoleNames().contains(roleName.toUpperCase());
    }

    public boolean isRoleAdmin (String roleName) {
        return Role.SUPER_ADMIN.getName().equals(roleName.toUpperCase());
    }


}
