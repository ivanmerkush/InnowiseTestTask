package ivanmerkush.services.impl;

import ivanmerkush.models.Roles;
import ivanmerkush.models.User;
import ivanmerkush.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private List<User> users;

    public UserServiceImpl() {
        users = new ArrayList<>();
    }


    @Override
    public User createUser(String name, String surname, String email, Roles roles, List<String> phones) {
        User user = new User(name, surname, email, roles, phones);
        users.add(user);
        return user;
    }

    @Override
    public User editUser(int index, User user) {
        users.set(index, user);
        return user;
    }

    @Override
    public boolean deleteUser(String name, String surname) {
        int size = users.size();
        users = users.stream()
                .filter(x -> !(x.getName().equals(name) && x.getSurname().equals(surname)))
                .collect(Collectors.toList());
        return !(users.size() == size);
    }

    @Override
    public User getUserByNameAndSurname(String name, String surname) {
        return users.stream()
                .filter(x -> x.getName().equals(name) && x.getSurname().equals(surname))
                .findFirst().orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public void setUsers(List<User> users) {
        this.users = users;
    }

}
