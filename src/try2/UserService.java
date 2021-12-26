package try2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserService {

    Scanner scanner = new Scanner(System.in);
    Validator validator = new Validator();

    public User create() {

        String name = newName("Please, enter name");
        String lastName = newName("Please, enter last name");
        String email = newEmail("Укажите email");
        List role = newRole("Укажите role");
        List phone = newPhone("Укажите номер телефона");

//        String role = ... ;   // Сделать проверку на согласование ролей
//        String phone = ... ;  // Сделать проверку на соответствие вводимого формата телефонов (допускается не более трех телефонов)

// Записать юзера в файл и отчитаться об успешной записи

        return new User(name, lastName, email, role, phone);
    }

    public User found(List<User> userList) {
        String email = newEmail("Напишите email юзера, которого хотите найти");
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public List remove(List<User> userList, String email) {
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                userList.remove(user);
                System.out.println("User was removed");
                return userList;
            }
        }
        System.out.println("User not found");
        return null;
    }

    public User edit(User user) {
        System.out.println("что будем редактировать?");
        System.out.println("name - select 1");
        System.out.println("last name - select 2");
        System.out.println("email - select 3");
        System.out.println("role - select 4");
        System.out.println("phone - select 5");

        // Пользователь ничего не выбрал !!!!!!!!!!!!!

        switch (scanner.nextLine()) {       // надо удалить юзеров с одинаковыми почтами!!!!!!!
            case ("1"): {
                return new User(newName("Введите новое имя"),
                        user.getLastName(),
                        user.getEmail(),
                        user.getRole(),
                        user.getPhoneNumber1());
            }
            case ("2"): {
                return new User(user.getFirstName(),
                        newName("Введите новую фамилию"),
                        user.getEmail(),
                        user.getRole(),
                        user.getPhoneNumber1());
            }
            case ("3"): {
                return new User(user.getFirstName(),
                        user.getLastName(),
                        newEmail("Введите новую почту"),
                        user.getRole(),
                        user.getPhoneNumber1());
            }
            case ("4"): {
                return new User(user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        newRole("Введите новую роль"),
                        user.getPhoneNumber1());
            }
            case ("5"): {
                return new User(user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getRole(),
                        newRole("Введите новый номер телефона"));
            }
        }
        return null;
    }

//------------------------------------------------------------------------------------------------------

    public String newName(String message) {           // Метод подходит как для создания имени, так и фамилии
        System.out.println(message);
        String name = scanner.nextLine();
        if (validator.validatorName(name)) {
            return name;
        } else {
            do {
                System.out.println("Допускаются только буквы латинского алфавита");
                name = scanner.nextLine();
            } while (!(validator.validatorName(name)));
        }
        return name;
    }

    public String newEmail(String message) {
        System.out.println(message);
        String email = scanner.nextLine();
        if (validator.validatorEmail(email)) {
            return email;
        } else {
            do {
                System.out.println("Неверно указана почта");
                email = scanner.nextLine();
            } while (!(validator.validatorEmail(email)));
        }
        return email;
    }

    public List<String> newRole(String message) {
        List<String> role = new ArrayList<>() ;
        System.out.println(message);
        String enterRole = scanner.nextLine();
        Pattern pattern = Pattern.compile(",");
        String [] array = pattern.split(enterRole);
        role = Arrays.asList(array);

        if (validator.validatorRole(role)) {
            return role;
        } else {
            do {
                System.out.println("Неверно указана role. Try again.");
                enterRole = scanner.nextLine();
                role = Arrays.asList(pattern.split(enterRole));
            } while (!(validator.validatorRole(role)));
        }
        return role;
    }

    public List newPhone(String message) {
        System.out.println(message);
        List<String> phone = new ArrayList<>() ;
        String enterPhone = scanner.nextLine();
        Pattern pattern = Pattern.compile(",");
        String [] array = pattern.split(enterPhone);
        phone = Arrays.asList(array);
        if (validator.validatorPhone(phone)) {
            return phone;
        } else {
            do {
                System.out.println("Допускаются только цифры и один пробел. К примеру, 37576 9851569");
                phone = Arrays.asList(pattern.split(enterPhone));
            } while (!(validator.validatorPhone(phone)));
        }
        return phone;
    }
}
