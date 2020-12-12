package ivanmerkush.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {

    private String name;
    private String surname;
    private String email;
    private Roles roles;
    private List<String> phones;

    public User() {

    }

    public User(String name, String surname, String email, Roles roles, List<String> phoneNumbers) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.roles = roles;
        this.phones = phoneNumbers;
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

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "User info:" +
                "\n1)Name - " + name  +
                ";\n2)Surname - " + surname  +
                ";\n3)Email - " + email  +
                ";\n4)" + roles.toString() +
                ";\n5)Phone numbers - " + phones.toString() +
                ";\n=================================\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) && surname.equals(user.surname) && email.equals(user.email) && roles.equals(user.roles) && phones.equals(user.phones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, email, roles, phones);
    }
}
