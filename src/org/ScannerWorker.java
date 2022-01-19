package org;

import java.util.Scanner;

public class ScannerWorker {

    private Scanner scanner;

    public ScannerWorker(Scanner scanner) {
        this.scanner = scanner;
    }

    public String editSelect() {
        System.out.println("What will we edit?");
        System.out.println("press 1 - name");
        System.out.println("press 2 - last name");
        System.out.println("press 3 - email");
        System.out.println("press 4 - role");
        System.out.println("press 5 - phone");
        System.out.println("Please, make your choice");
        return scanner.nextLine().trim();
    }

    public String mainSelect() {
        System.out.println("Hi! What would you like?");
        System.out.println("press '1' - Creat new user ");
        System.out.println("press '2' - Find/watch user ");
        System.out.println("press '3' - Edin user ");
        System.out.println("press '4' - Remove user ");
        System.out.println("press '5' - View all users ");
        System.out.println("Please, make your choice");
        return scanner.nextLine().trim();
    }

    public String newUser(String message) {
        System.out.println(message);
        return scanner.nextLine().trim();
    }
}
