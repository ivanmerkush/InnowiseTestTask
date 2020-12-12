package ivanmerkush.controllers;

import ivanmerkush.models.User;
import ivanmerkush.services.FileService;
import ivanmerkush.services.UserService;

import java.util.List;
import java.util.regex.Pattern;

public abstract class Controller {
    protected UserService userService;
    protected FileService fileService;
    protected final Pattern emailPattern = Pattern.compile("^(.+)+@(.+)+\\.(.+)$");
    protected final Pattern phonePattern = Pattern.compile("^(375)+\\d{9}");

    public Controller(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    public abstract List<User> findAllUsers();
    public abstract User findUser();
    public abstract User createUser();
    public abstract User editUser();
    public abstract void deleteUser();
    public abstract List<User> loadUsers();
    public abstract void saveUsers();
}
