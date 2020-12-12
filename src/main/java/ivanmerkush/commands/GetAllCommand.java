package ivanmerkush.commands;

import ivanmerkush.controllers.Controller;
import ivanmerkush.models.User;
import ivanmerkush.views.View;

import java.util.List;

public class GetAllCommand extends Command{

    public GetAllCommand(View view, Controller controller) {
        super(view, controller);
    }

    @Override
    public List<User> execute() {
        return controller.findAllUsers();
    }
}
