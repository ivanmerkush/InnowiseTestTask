package ivanmerkush.services;

import ivanmerkush.models.Roles;
import ivanmerkush.models.User;

import java.util.List;

public interface UserService {
    User createUser(String name, String surname, String email, Roles roles, List<String> phones);
    User editUser(int index, User user);
    boolean deleteUser(String name, String surname);
    User getUserByNameAndSurname(String name, String surname);
    List<User> getAllUsers();
    void setUsers(List<User> users);
}
