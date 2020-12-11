package ivanmerkush.service;

import ivanmerkush.model.User;

import java.util.List;

public interface FileService {
    void write(List<User> users);
    List<User> read();
}
