package try2;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Reception {


    Scanner scanner = new Scanner(System.in);
    UserService userService = new UserService();
    Repository repository = new Repository();

    void mainChoice() throws IOException, ClassNotFoundException {
        System.out.println("Hi! What would you like?");
        System.out.println(" - Creat new user - select '1'");
        System.out.println(" - Find/watch user - select '2'");
        System.out.println(" - Edin user - select '3'");
        System.out.println(" - Remove user - select '4'");
        System.out.println(" - View all users - select '5'");

        // Пользователь ничего не выбрал !!!!!!!!!!!!!

        switch (scanner.nextLine()) {
            case ("1"):             // Creat
                User newUser = userService.create();
                repository.saveUser(newUser);
                System.out.println(newUser);
                System.out.println("User was created");
                break;
            case ("2"):             // Find
                User searchedUser = userService.found(repository.readUserBook());
                System.out.println(searchedUser == null ? "юзер не найден" : searchedUser);
                break;
            case ("3"):             // Edit
                User editableUser = userService.found(repository.readUserBook());
                if (editableUser != null) {
                    User userEdited = userService.edit(editableUser);
                    repository.saveUser(userEdited);
                    System.out.println(userEdited);
                    System.out.println("Юзер был успешно отредактирован и сохранен");
                    userService.remove(repository.readUserBook(), userEdited.getEmail());
                } else {
                    System.out.println("юзер не найден");
                }
                break;
            case ("4"):             // Remove
                repository.saveList(userService.remove(repository.readUserBook(), userService.newEmail("Введите почту юзера, которого будем удалять")));
                // todo кажется этот метод удаляет всё в подряд. перепроверь!
                break;
            case ("5"):             // ShowAll
                List <User> allUser = repository.readUserBook();
                for(User s : allUser) {
                    System.out.println(s);
                }
                break;
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Reception reception = new Reception();
        reception.mainChoice();
    }
}