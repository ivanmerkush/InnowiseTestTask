package ivanmerkush.views;

import ivanmerkush.models.User;

public interface Printer {
    void print(String text);
    void printUser(User user);
}
