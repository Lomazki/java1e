package org;

import org.validation.ValidationPhone;
import org.validation.ValidationRole;
import org.validation.Validator;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserService {

    Scanner scanner = new Scanner(System.in);
    Validator validator = new Validator();
    ValidationRole validationRole = new ValidationRole();
    ValidationPhone validationPhone = new ValidationPhone();

    public User create() throws IOException, ClassNotFoundException {

        String name = newName("Please, enter name");
        String lastName = newName("Please, enter last name");
        String email = newEmail("Enter email");
        List role = newRole("Enter role(s) separated by commas");
        List phone = newPhone("Enter phone number(s) separated by commas");

        return new User(name, lastName, email, role, phone);
    }

    public User found(List<User> userList) {
        System.out.println("Enter the user's email");
        String email = scanner.nextLine().trim();
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
                return userList;
            }
        }
        System.out.println("User not found");
        return null;
    }

    public List remove(List<User> userList) {
        System.out.println("Enter the email of the user that we will delete");
        String email = scanner.nextLine();
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                userList.remove(user);
                return userList;
            }
        }
        System.out.println("User not found");
        return null;
    }

    public User edit(User user) throws IOException, ClassNotFoundException {
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
                        newRole("Write a new phone number(es)"));
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
                System.out.println("Only letters of the Latin alphabet are allowed");
                name = scanner.nextLine();
            } while (!(validator.validatorName(name)));
        }
        return name;
    }

    public String newEmail(String message) throws IOException, ClassNotFoundException {
        System.out.println(message);
        String email = scanner.nextLine().trim();
        if (validator.validatorEmail(email) && !validator.emailExist(email)) {
            return email;
        } else {
            do {
                System.out.println("The email address is incorrect");
                email = scanner.nextLine();
            } while (!(validator.validatorEmail(email)) || validator.emailExist(email));
        }
        return email;
    }

    public List<String> newRole(String message) {
        List<String> roleList;
        System.out.println(message);
        String enterRole = scanner.nextLine();
        Pattern pattern = Pattern.compile(",");
        String[] array = pattern.split(enterRole.trim().toUpperCase());
        roleList = Arrays.asList(array);

        if (validationRole.validate(roleList)) {
            return roleList;
        } else {
            do {
                System.out.println("The role is incorrect. Try again.");
                enterRole = scanner.nextLine();
                roleList = Arrays.asList(pattern.split(enterRole.trim().toUpperCase()));
            } while (!(validationRole.validate(roleList)));
        }
        return roleList;
    }

    public List<String> newPhone(String message) {
        System.out.println(message);
        String enterPhone = scanner.nextLine();
        Pattern pattern = Pattern.compile(",");
        String[] arrayPhone = pattern.split(enterPhone.trim());
        if (validationPhone.validator(arrayPhone)) {
            return Arrays.asList(arrayPhone);
        } else {
            do {
                System.out.println("Try again");
                enterPhone = scanner.nextLine();
                arrayPhone = (pattern.split(enterPhone));
            } while (!(validationPhone.validator(arrayPhone)));
        }
        return Arrays.asList(arrayPhone);
    }
}
