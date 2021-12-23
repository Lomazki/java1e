package rubbish.try1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reception {

    void mainChoice(){
        System.out.println("Hi! what would you like?");
        System.out.println(" - Creat new user - select '1'");
        System.out.println(" - Find/watch user - select '2'");
        System.out.println(" - Edin user - select '3'");
        System.out.println(" - Remove user - select '4'");
        System.out.println(" - Look all user - select '5'");
    }

    void creatNewUserDialog(){
        //System.out.println("Пример: Иван, Иваненко, ivanivanenko@email.com, CUSTOMER, 37500 1234567");
    }

    void newName(){
        System.out.println("Укажите имя");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        StringBuilder sb = new StringBuilder(name.toUpperCase());
        Pattern pattern = Pattern.compile("[A-ZА-ЯЁ']");
        Matcher matcher = pattern.matcher(sb);

        // Сделать проверку на корректность вводимого имени
        // Записать все данные пользователя в массив и сохранить в файл
    }

    void newLastName(){
        System.out.println("Укажите фамилию");
        Scanner scanner = new Scanner(System.in);
        String lastName = scanner.nextLine();
        StringBuilder sb = new StringBuilder(lastName);
    }

    void newEmail(){
        System.out.println("Укажите email");
        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();
        StringBuilder sb = new StringBuilder(email);
    }

    void newRole(){
        System.out.println("Укажите role");
        Scanner scanner = new Scanner(System.in);
        String role = scanner.nextLine();
        StringBuilder sb = new StringBuilder(role);
    }

    void newPhone(){
        System.out.println("Укажите номер телефона или несколько номеров через запятую");
        Scanner scanner = new Scanner(System.in);
        String phone = scanner.nextLine();
        StringBuilder sb = new StringBuilder(phone);
    }

}
