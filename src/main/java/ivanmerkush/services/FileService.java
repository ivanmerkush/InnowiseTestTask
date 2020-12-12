package ivanmerkush.services;

import ivanmerkush.models.User;

import java.util.List;

public interface FileService {
    String PATH_TO_FILE = "./src/main/resources/users.txt";
    void write(List<User> users);
    List<User> read();
}
