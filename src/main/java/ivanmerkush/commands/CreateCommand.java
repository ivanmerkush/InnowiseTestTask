package ivanmerkush.commands;

import ivanmerkush.controllers.Controller;
import ivanmerkush.models.User;
import ivanmerkush.views.View;

import java.util.ArrayList;
import java.util.List;

public class CreateCommand extends Command {

    public CreateCommand(View view, Controller controller) {
        super(view, controller);
    }
    @Override
    public List<User> execute() {
        return new ArrayList<User>(){{
            add(controller.createUser());
        }};
    }
}
