package rubbish.try1.database;

import rubbish.try1.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class CreateUser {

//      System.out.println("Пример: Иван, Иваненко, ivanivanenko@email.com, CUSTOMER, 37500 1234567");
//      List<String> userArray = new ArrayList<>();

    public static void start() {
        String name = scanEnter("Please, enter your name");
        String lastName = scanEnter("Please, enter your lastName");
        String email = scanEnter("Please, enter your Email"); // Сделать проверку на соответствие вводимой почты
        String role = scanEnter("Please, enter your Role");   // Сделать проверку на согласование ролей
        String phone = scanEnter("Please, enter your Phone"); // Сделать проверку на соответствие вводимого формата телефонов (допускается не более трех телефонов)
        User user = new User(name, lastName, email, role, phone);  // Записать юзера в файл и отчитаться об успешной записи
    }

    // Записываем в файл
    static void writeInFile(User res) throws IOException {
        Path path = Path.of("resources", "base.txt");
        Files.write(path, (Iterable<? extends CharSequence>) res);      // не уверен, что это сработает
    }

    static String scanEnter(String text) {
        System.out.println(text);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();

//        StringBuilder sb = new StringBuilder(name.toUpperCase());
//        Pattern pattern = Pattern.compile("[A-ZА-ЯЁ']");
//        Matcher matcher = pattern.matcher(sb);

    }
}
