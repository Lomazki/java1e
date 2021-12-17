package try2;

/*
    1) Создать юзера
    2) Записать в файл
    3) Считать с файла

 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Runner implements Serializable {

    private static final long serialVersionUID = -7422177151823659507L;

    public static void main(String[] args) throws IOException {

        List<User> users = new ArrayList<>();
        UserService userService = new UserService();
        User newUser = userService.create("Sasha", "Ivanov", "sashaivanov@mail.com", "ADMIN", "+37529 1112233");
        userService.saveUser(users, newUser);
    }
}
