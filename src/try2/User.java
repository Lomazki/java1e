package try2;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private static final long serialVersionUID = -4811399492990244810L;
    String firstName;
    String lastName;
    String email;
    List<String> role;
    List<String> phoneNumber1;

    public User(String firstName, String lastName, String email, List role, List phoneNumber1) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.phoneNumber1 = phoneNumber1;
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

    public List getRole() {
        return role;
    }

    public List getPhoneNumber1() {
        return phoneNumber1;
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
