package ivanmerkush.model;

import java.util.List;

public class User {
    private String name;
    private String surname;
    private String email;
    private List<String> roles;
    private List<String> phoneNumbers;

    public User() {

    }

    public User(String name, String surname, String email, List<String> roles, List<String> phoneNumbers) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.roles = roles;
        this.phoneNumbers = phoneNumbers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public String toString() {
        return "User info:" +
                "\n1)Name - " + name  +
                ";\n2)Surname - " + surname  +
                ";\n3)Email - " + email  +
                ";\n4)Roles - " + roles.toString() +
                ";\n5)Phone numbers - " + phoneNumbers.toString() +
                ";\n=================================\n";
    }
}
