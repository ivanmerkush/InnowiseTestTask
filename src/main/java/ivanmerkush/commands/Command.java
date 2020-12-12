package ivanmerkush.commands;

import ivanmerkush.models.User;
import ivanmerkush.views.View;
import ivanmerkush.controllers.Controller;

import java.util.List;

public abstract class Command {
    public View view;
    public Controller controller;

    public Command(View view, Controller controller) {
        this.view = view;
        this.controller = controller;
    }

    public abstract List<User> execute();
}
