package main.database;

/*

 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateUser {

    //System.out.println("Пример: Иван, Иваненко, ivanivanenko@email.com, CUSTOMER, 37500 1234567");

    //newName, lastName и т.д. нужно вносить в конструктор User

    // Создать массив для хранения users
    List<String> user = new ArrayList<>();

    public static void start() {
        newName();
        newLastName();
        newEmail();
        newRole();
        newPhone();
    }

    // Записываем в файл
    static void writeInFile(String res) throws IOException {
        Path path = Path.of("resources", "base.txt");
        Files.writeString(path, res);
    }

    static void newName(){
        System.out.println("Укажите имя");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

//        StringBuilder sb = new StringBuilder(name.toUpperCase());
//        Pattern pattern = Pattern.compile("[A-ZА-ЯЁ']");
//        Matcher matcher = pattern.matcher(sb);

        // Сделать проверку на корректность вводимого имени
        // Записать все данные пользователя в массив и сохранить в файл
    }

    static void newLastName(){
        System.out.println("Укажите фамилию");
        Scanner scanner = new Scanner(System.in);
        String lastName = scanner.nextLine();
        StringBuilder sb = new StringBuilder(lastName);
    }

    static void newEmail(){
        System.out.println("Укажите email");
        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();
        StringBuilder sb = new StringBuilder(email);
    }

    static void newRole(){
        System.out.println("Укажите role");
        Scanner scanner = new Scanner(System.in);
        String role = scanner.nextLine();
        StringBuilder sb = new StringBuilder(role);
    }

    static void newPhone(){
        System.out.println("Укажите номер телефона или несколько номеров через зяпятую");
        Scanner scanner = new Scanner(System.in);
        String phone = scanner.nextLine();
        StringBuilder sb = new StringBuilder(phone);
    }

}
