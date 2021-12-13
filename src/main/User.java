package main;

import javax.management.relation.Role;

public class User {

    String firstName;
    String lastName;
    String email;
    Role role;
    int phoneNumber1;
    int phoneNumber2;
    int phoneNumber3;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(int phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public int getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(int phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public int getPhoneNumber3() {
        return phoneNumber3;
    }

    public void setPhoneNumber3(int phoneNumber3) {
        this.phoneNumber3 = phoneNumber3;
    }

    public User(String firstName, String lastName, String email, Role role, int phoneNumber1) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.phoneNumber1 = phoneNumber1;
    }

    public User(String firstName, String lastName, String email, Role role, int phoneNumber1, int phoneNumber2) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
    }

    public User(String firstName, String lastName, String email, Role role, int phoneNumber1, int phoneNumber2, int phoneNumber3) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
        this.phoneNumber3 = phoneNumber3;
    }
}
