package try2;

    /*
       При запуске диалоговое окно:
       Что хотите:
       - создать пользователя
       - редактировать пользователя
            - имя
            - фамилию
            - email
            - роль
            - телефон
     */

/*
    1) Создать юзера
    2) Записать в файл
    3) Считать с файла

 */

import java.io.*;

public class Runner implements Serializable {

    private static final long serialVersionUID = -7422177151823659507L;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        UserService userService = new UserService();        // Основные операции с юзером
        Repository repository = new Repository();           // Хранилище
        Reception reception = new Reception();
        reception.mainChoice();

//        User newUser = userService.create("Sasha", "Ivanov", "sashaivanov@mail.com", "ADMIN", "+37529 1112233");
//        repository.saveUser(users, newUser);
//        User newUser1 = userService.create("11Sasha", "11Ivanov", "11sashaivanov@mail.com", "11ADMIN", "+37529 9999999");
//        repository.saveUser(users, newUser1);
//        System.out.println(userService.found(users,"sashaivanov@mail.com"));
//        System.out.println(userService.remove(users,"sashaivanov@mail.com"));
    }
}
