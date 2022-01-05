package org;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private static final long serialVersionUID = -4453135444776024875L;

    String firstName;
    String lastName;
    String email;
    List<String> role;
    List<String> phone;

    public User(String firstName, String lastName, String email, List<String> role, List<String> phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<String> getRole() {
        return role;
    }

    public List<String> getPhoneNumber1() {
        return phone;
    }

    @Override
    public String toString() {
        return "User " +
                "{firstName = " + firstName +
                ", \n      lastName  = " + lastName +
                ", \n      email     = " + email +
                ", \n      role      = " + role +
                ", \n      phone     = " + phone + "}";
    }
}
