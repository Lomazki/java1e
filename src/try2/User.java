package try2;

import java.io.Serializable;

public class User implements Serializable {

    String firstName;
    String lastName;
    String email;
    String role;
    String phoneNumber1;

    public User(String firstName, String lastName, String email, String role, String phoneNumber1) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.phoneNumber1 = phoneNumber1;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", phoneNumber1='" + phoneNumber1 + '\'' +
                '}';
    }
}
