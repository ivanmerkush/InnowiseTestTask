package ivanmerkush.commands;

import ivanmerkush.models.User;
import ivanmerkush.services.UserService;
import ivanmerkush.views.View;


public class GetCommand extends Command{

    public GetCommand(View view, UserService userService) {
        super(view, userService);
    }

    @Override
    public void execute() {
        String[] name = inputNameSurname();
        User user = userService.getUserByNameAndSurname(name[0], name[1]);
        if(user != null) {
            view.print("User's info:");
            view.printUser(user);
        }
        else {
            view.print("User doesn't exist");
        }
    }
}
