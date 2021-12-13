package main.database;

/*

 */

import javax.management.relation.Role;

public class CreateUser {

    int id;
    String firstName;
    String lastName;
    String email;
    Role role;
    int phoneNumber1;
    int phoneNumber2;
    int phoneNumber3;

    public CreateUser(int id, String firstName, String lastName, String email, Role role, int phoneNumber1) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.phoneNumber1 = phoneNumber1;
    }

    public CreateUser(int id, String firstName, String lastName, String email, Role role, int phoneNumber1, int phoneNumber2) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
    }

    public CreateUser(int id, String firstName, String lastName, String email, Role role, int phoneNumber1, int phoneNumber2, int phoneNumber3) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
        this.phoneNumber3 = phoneNumber3;
    }
}
