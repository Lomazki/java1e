package org;

import org.validation.EmailValidate;
import org.validation.ValidatorError;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static org.validation.ExceptionMessage.*;

public class Reception {

    Scanner scanner = new Scanner(System.in);
    UserService userService = new UserService();
    Repository repository = new Repository();
    EmailValidate emailValidate = new EmailValidate();

    public Reception() throws IOException, ClassNotFoundException {
    }

    void mainChoice() throws IOException, ClassNotFoundException {
        String choice;
        System.out.println("Hi! What would you like?");
        do {
            System.out.println(" - Creat new user - select '1'");
            System.out.println(" - Find/watch user - select '2'");
            System.out.println(" - Edin user - select '3'");
            System.out.println(" - Remove user - select '4'");
            System.out.println(" - View all users - select '5'");
            System.out.println("Please, make your choice");
            choice = scanner.nextLine().trim();
        } while (!(choice.equals("1") ||
                choice.equals("2") ||
                choice.equals("3") ||
                choice.equals("4") ||
                choice.equals("5")));

        switch (choice) {
            case ("1"):             // Creat
                User newUser = userService.create();
                repository.getUserList().add(newUser);
                repository.saveList(repository.getUserList());
                System.out.println(String.format(USER_CREATED, newUser));
                break;
            case ("2"):             // Find
                User searchedUser;
                System.out.println("Enter the email of the user that we will to delete");
                String emailFind = scanner.nextLine().trim();
                ValidatorError findValidErrorEmail = emailValidate.validator(emailFind);
                if (findValidErrorEmail == null) {
                    searchedUser = userService.found(repository.getUserList(), emailFind);
                    if (searchedUser == null) {
                        System.out.println(String.format(USER_NOT_FOUND, emailFind));
                    } else {
                        System.out.println(searchedUser);
                    }
                }else {
                    System.out.println(findValidErrorEmail.getMessage());
                }
                break;
            case ("3"):             // Edit
                System.out.println("Enter the email of the user that we will to edit");
                String emailEdit = scanner.nextLine().trim();
                ValidatorError editValidErrorEmail = emailValidate.validator(emailEdit);
                if (editValidErrorEmail == null) {
                    User editableUser = userService.found(repository.getUserList(), emailEdit);
                    if (editableUser != null) {
                        User userEdited = userService.edit(editableUser);
                        repository.getUserList().add(userEdited);
                        List<User> newUserList = userService.remove(repository.getUserList(), editableUser.getEmail());
                        repository.saveList(newUserList);
                        System.out.println(String.format(USER_WAS_EDITED, userEdited));
                    } else {
                        System.out.println(String.format(USER_NOT_FOUND, emailEdit));
                    }
                } else {
                    System.out.println(editValidErrorEmail.getMessage());
                }
                break;
            case ("4"):             // Remove
                System.out.println("Enter the email of the user that we will to delete");
                String emailRemove = scanner.nextLine().trim();
                ValidatorError removeValidErrorEmail = emailValidate.validator(emailRemove);
                if (removeValidErrorEmail == null){
                    repository.saveList(userService.remove(repository.getUserList(), emailRemove));
                    System.out.println(String.format(USER_WAS_REMOVED, emailRemove));
                    System.out.println("User was removed");
                }else {
                    System.out.println(removeValidErrorEmail.getMessage());
                }
                break;
            case ("5"):             // ShowAll
                List<User> allUser = repository.getUserList();
                for (User s : allUser) {
                    System.out.println(s);
                }
                break;
        }
    }
}
