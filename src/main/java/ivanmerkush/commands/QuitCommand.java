package ivanmerkush.commands;

import ivanmerkush.controllers.Controller;
import ivanmerkush.models.User;
import ivanmerkush.views.View;

import java.util.ArrayList;
import java.util.List;

public class QuitCommand extends Command{

    public QuitCommand(View view, Controller controller) {
        super(view, controller);
    }

    @Override
    public List<User> execute() {
        System.exit(0);
        return new ArrayList<>();
    }
}