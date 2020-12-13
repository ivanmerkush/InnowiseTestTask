package ivanmerkush.commands;

import ivanmerkush.models.Roles;
import ivanmerkush.models.User;
import ivanmerkush.services.UserService;
import ivanmerkush.views.View;

import java.util.List;

public class EditCommand extends Command{

    public EditCommand(View view, UserService userService) {
        super(view, userService);
    }

    public User editUser() {
        String[] name = inputNameSurname();
        User user = userService.getUserByNameAndSurname(name[0], name[1]);
        int index = userService.getAllUsers().indexOf(user);
        view.printUser(user);
        view.print("What would you like to change?");
        int number = Integer.parseInt(view.read());
        return editValues(number,index,user);
    }

    private User editValues(int number,int index, User user) {
        switch (number) {
            case 1:
                view.print("Enter new name:");
                user.setName(view.read());
                break;
            case 2:
                view.print("Enter new surname:");
                user.setSurname( view.read());
                break;
            case 3:
                user.setEmail(addEmail());
                break;
            case 4:
                Roles roles = addRoles();
                user.setRoles(roles);
                break;
            case 5:
                List<String> phones = addPhones();
                user.setPhones(phones);
                break;
            default:
                view.print("Nothing changed.");
                return user;
        }
        userService.editUser(index, user);
        view.print("User edited.");
        return user;
    }


    @Override
    public void execute() {

        view.printUser(editUser());
    }
}
