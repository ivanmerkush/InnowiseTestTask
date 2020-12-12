package ivanmerkush.commands;

import ivanmerkush.controllers.Controller;
import ivanmerkush.models.User;
import ivanmerkush.views.View;

import java.util.ArrayList;
import java.util.List;

public class SaveCommand extends Command{

    public SaveCommand(View view, Controller controller) {
        super(view, controller);
    }

    @Override
    public List<User> execute() {
        controller.saveUsers();
        return new ArrayList<>();
    }
}
