package org;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Reception {

    Scanner scanner = new Scanner(System.in);
    UserService userService = new UserService();
    Repository repository = new Repository();

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
                repository.saveUser(newUser);
                System.out.print(newUser);
                System.out.println(" was created");
                break;
            case ("2"):             // Find
                User searchedUser = userService.found(repository.readUserBook());
                System.out.println(searchedUser == null ? "User not found" : searchedUser);
                break;
            case ("3"):             // Edit
                User editableUser = userService.found(repository.readUserBook());
                if (editableUser != null) {
                    String emailUserEdit = editableUser.getEmail();
                    User userEdited = userService.edit(editableUser);
                    repository.saveUser(userEdited);
                    System.out.println(userEdited);
                    System.out.println("User was edited and saved");
                    repository.saveList(userService.remove(repository.readUserBook(), emailUserEdit));
                } else {
                    System.out.println("User not found");
                }
                break;
            case ("4"):             // Remove
                repository.saveList(userService.remove(repository.readUserBook()));
                System.out.println("User was removed");
                break;
            case ("5"):             // ShowAll
                List<User> allUser = repository.readUserBook();
                for (User s : allUser) {
                    System.out.println(s);
                }
                break;
        }
    }
}