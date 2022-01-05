package org;

import org.validation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static org.validation.ExceptionMessage.USER_IS_NULL;
import static org.validation.ExceptionMessage.USER_LIST_IS_NULL;

public class UserService {

    Scanner scanner = new Scanner(System.in);
    NameValidate nameValidate = new NameValidate();
    RoleValidate roleValidate = new RoleValidate();
    PhoneValidate phoneValidate = new PhoneValidate();
    EmailValidate emailValidate = new EmailValidate();

    public User create() throws IOException, ClassNotFoundException {

        String name = newName("Please, enter name");
        String lastName = newName("Please, enter last name");
        String email = newEmail("Enter email");
        List<String> role = newRole("Enter role(s) separated by commas");
        List<String> phone = newPhone("Enter phone number(s) separated by commas");

        return new User(name, lastName, email, role, phone);
    }

    public User found(List<User> userList, String email) {
        if (userList == null || userList.size() == 0) {
            System.out.println(USER_LIST_IS_NULL);
            return null;
        }
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public List<User> remove(List<User> userList, String email) {
        if (userList == null || userList.size() == 0) {
            System.out.println(USER_LIST_IS_NULL);
            return null;
        }
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                userList.remove(user);
                return userList;
            }
        }
        return null;
    }

    public User edit(User user) throws IOException, ClassNotFoundException {

        if (user == null) {
            System.out.println(USER_IS_NULL);
            return null;
        }
        String choice;
        System.out.println("What will we edit?");
        do {
            System.out.println("name - select 1");
            System.out.println("last name - select 2");
            System.out.println("email - select 3");
            System.out.println("role - select 4");
            System.out.println("phone - select 5");
            System.out.println("Please, make your choice");
            choice = scanner.nextLine().trim();
        } while ((!(choice.equals("1") ||
                choice.equals("2") ||
                choice.equals("3") ||
                choice.equals("4") ||
                choice.equals("5"))));

        switch (choice) {
            case ("1"): {
                return new User(newName("Write a new name"),
                        user.getLastName(),
                        user.getEmail(),
                        user.getRole(),
                        user.getPhoneNumber1());
            }
            case ("2"): {
                return new User(user.getFirstName(),
                        newName("Write a new last name"),
                        user.getEmail(),
                        user.getRole(),
                        user.getPhoneNumber1());
            }
            case ("3"): {
                return new User(user.getFirstName(),
                        user.getLastName(),
                        newEmail("write a new email"),
                        user.getRole(),
                        user.getPhoneNumber1());
            }
            case ("4"): {
                return new User(user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        newRole("Write a new role(s)"),
                        user.getPhoneNumber1());
            }
            case ("5"): {
                return new User(user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getRole(),
                        newPhone("Write a new phone number(es)"));
            }
        }
        return null;
    }

//------------------------------------------------------------------------------------------------------

    public String newName(String message) {           // Метод подходит как для создания имени, так и фамилии
        System.out.println(message);
        String name = scanner.nextLine().trim();
        if (nameValidate.validator(name) == null) {
            return name;
        } else {
            do {
                System.out.println("Only letters of the Latin alphabet are allowed");
                name = scanner.nextLine().trim();
            } while ((nameValidate.validator(name)) != null);
        }
        return name;
    }

    public String newEmail(String message) throws IOException, ClassNotFoundException {
        System.out.println(message);
        String email = scanner.nextLine().trim();

        if (emailValidate.validatorEmail(email) == null && emailValidate.emailIsExist(email) == null) {
            return email;
        } else {
            do {
                System.out.println("Try again");
                email = scanner.nextLine().trim();
            } while (emailValidate.validatorEmail(email) != null || emailValidate.emailIsExist(email) != null);
        }
        return email;
    }

    public List<String> newRole(String message) {
        List<String> roleList;
        System.out.println(message);
        String enterRole = scanner.nextLine().trim();
        Pattern pattern = Pattern.compile(",");
        String[] array = pattern.split(enterRole.trim().toUpperCase());
        roleList = Arrays.asList(array);

        if ((roleValidate.validate(roleList)) == null) {
            return roleList;
        } else {
            do {
                System.out.println("Try again");
                enterRole = scanner.nextLine().trim();
                roleList = Arrays.asList(pattern.split(enterRole.trim().toUpperCase()));
            } while ((roleValidate.validate(roleList)) != null);
        }
        return roleList;
    }

    public List<String> newPhone(String message) {
        System.out.println(message);
        String enterPhone = scanner.nextLine().trim();
        Pattern pattern = Pattern.compile(",");
        String[] arrayPhone = pattern.split(enterPhone.trim());
        if ((phoneValidate.validator(Arrays.asList(arrayPhone))) == null) {
            return Arrays.asList(arrayPhone);
        } else {
            do {
                System.out.println("Try again");
                enterPhone = scanner.nextLine();
                arrayPhone = (pattern.split(enterPhone));
            } while ((phoneValidate.validator(Arrays.asList(arrayPhone))) != null);
        }
        return Arrays.asList(arrayPhone);
    }
}
