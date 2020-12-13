package ivanmerkush.commands;

import ivanmerkush.services.UserService;
import ivanmerkush.views.View;


public class DeleteCommand extends Command{

    public DeleteCommand(View view, UserService userService) {
        super(view, userService);
    }

    public void deleteUser() {
        String[] name = inputNameSurname();
        userService.deleteUser(name[0], name[1]);
        view.print("User deleted");
    }

    @Override
    public void execute() {
        deleteUser();
    }
}
