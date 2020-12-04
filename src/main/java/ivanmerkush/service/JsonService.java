package ivanmerkush.service;

import ivanmerkush.model.User;

import java.util.List;

public interface JsonService {

    void write(List<User> users);
    List<User> read();
}
