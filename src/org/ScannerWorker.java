package org;

import java.util.Scanner;

public class ScannerWorker {

    Scanner scanner = new Scanner(System.in);

    public String editSelect() {
        System.out.println("name - select 1");
        System.out.println("last name - select 2");
        System.out.println("email - select 3");
        System.out.println("role - select 4");
        System.out.println("phone - select 5");
        System.out.println("Please, make your choice");
        return scanner.nextLine().trim();
    }

    public String mainSelect() {
        System.out.println(" - Creat new user - select '1'");
        System.out.println(" - Find/watch user - select '2'");
        System.out.println(" - Edin user - select '3'");
        System.out.println(" - Remove user - select '4'");
        System.out.println(" - View all users - select '5'");
        System.out.println("Please, make your choice");
        return scanner.nextLine().trim();
    }

    public String getEmail(String message) {
        System.out.println(message);
        return scanner.nextLine().trim();
    }

    public String newUser(String message) {
        System.out.println(message);
        return scanner.nextLine().trim();
    }
}
