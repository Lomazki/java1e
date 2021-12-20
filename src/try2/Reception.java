package try2;

 /*
    Диалог с пользователем

    К примеру:
     - ввел имя => валидация => сохранил в переменную
     - ввел фамили => ...
     ...
      userService.create(name, lastName, ... )
 */
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reception {

    Scanner scanner = new Scanner(System.in);
    Validator validator = new Validator();

    void mainChoice(){
        System.out.println("Hi! what would you like?");
        System.out.println(" - Creat new user - select '1'");
        System.out.println(" - Find/watch user - select '2'");
        System.out.println(" - Edin user - select '3'");
        System.out.println(" - Remove user - select '4'");
        System.out.println(" - Look all user - select '5'");

        switch (scanner.nextLine()) {
            case ("1"):
                newUser();
                break;
            case ("2"):
//                FindUser.start();
                break;
            case ("3"):
//                EditUser.start();
                break;
            case ("4"):
//                RemoveUser.start();
                break;
            case ("5"):
//                ShowAllUsers.start();
                break;
        }
    }

    public void newUser() {
        newName("Please, enter your name");

//        System.out.println("Please, enter your name");
//        String name = validator.validatorName(scanner.nextLine());
//        System.out.println("Please, enter your lastName");
//        String lastName = validator.validatorLastName(scanner.nextLine());
//        System.out.println("Please, enter your Email");
//        String email = validator.validatorEmail(scanner.nextLine()); // Сделать проверку на соответствие вводимой почты
//        System.out.println("Please, enter your Role");
//        String role = validator.validatorRole(scanner.nextLine());   // Сделать проверку на согласование ролей
//        System.out.println("Please, enter your Phone");
//        String phone = validator.validatorPhone(scanner.nextLine()); // Сделать проверку на соответствие вводимого формата телефонов (допускается не более трех телефонов)
//        User user = new User(name, lastName, email, role, phone);  // Записать юзера в файл и отчитаться об успешной записи
    }

    public String newName(String message){
        String name;
        System.out.println(message);
        do {
            System.out.println("Допускаются только буквы русского и латинского алфавита");
            name = scanner.nextLine();
        } while (!(validator.validatorName(name)));
        return name;


//        StringBuilder sb = new StringBuilder(scanner.nextLine().toUpperCase());
//        Pattern pattern = Pattern.compile("[A-ZА-ЯЁ']");
//        Matcher matcher = pattern.matcher(sb);
    }

    void newLastName(){
        System.out.println("Укажите фамилию");
    }

    void newEmail(){
        System.out.println("Укажите email");
    }

    void newRole(){
        System.out.println("Укажите role");
    }

    void newPhone(){
        System.out.println("Укажите номер телефона или несколько номеров через запятую");
    }
}
