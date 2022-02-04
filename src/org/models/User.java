package src.org.models;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private static final long serialVersionUID = -4453135444776024875L;

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Role> role;
    private List<String> phone;

    public User(long id, String firstName, String lastName, String email, List<Role> role, List<String> phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<Role> getRoles() {
        return role;
    }

    public List<String> getPhoneNumber() {
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

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }
}
