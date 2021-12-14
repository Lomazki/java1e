package main;

    /* При запуске диалоговое окно:
       Что хотите:
       - создать пользователя
       - найти\показать пользователя
            - ввести имя и фамилию или id
       - редактировать пользователя
            - имя
            - фамилию
            - роль
            - email
            - телефон
       - удалить пользователя
            - ввести имя и фамилию или id
       - посмотреть всех пользователей
     */

public class Main {
    public static void main(String[] args) {

        Reception reception  = new Reception();
        Service service = new Service();
        reception.mainChoice();
        service.mainChoice(service.entered());

    }
}
