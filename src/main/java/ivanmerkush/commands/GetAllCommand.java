package ivanmerkush.commands;

import ivanmerkush.models.User;
import ivanmerkush.services.UserService;
import ivanmerkush.views.View;


public class GetAllCommand extends Command{

    public GetAllCommand(View view, UserService userService) {
        super(view, userService);
    }

    @Override
    public void execute() {
        view.print("All users:");
        for(User i: userService.getAllUsers()) {
            view.printUser(i);
        }
    }
}
