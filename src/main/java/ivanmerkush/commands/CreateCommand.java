package ivanmerkush.commands;

import ivanmerkush.models.Roles;
import ivanmerkush.services.UserService;
import ivanmerkush.views.View;

import java.util.List;

public class CreateCommand extends Command {

    public CreateCommand(View view, UserService userService) {
        super(view, userService);
    }


    @Override
    public void execute() {
        String[] name = inputNameSurname();
        String email = addEmail();
        Roles roles = addRoles();
        List<String> phones = addPhones();
        view.print("User created");
        view.printUser(userService.createUser(name[0], name[1], email, roles, phones));
    }
}
