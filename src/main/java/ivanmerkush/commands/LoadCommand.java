package ivanmerkush.commands;

import ivanmerkush.models.User;
import ivanmerkush.services.FileService;
import ivanmerkush.services.UserService;
import ivanmerkush.views.View;

import java.util.List;

public class LoadCommand extends Command{

    private final FileService fileService;

    public LoadCommand(View view, UserService userService, FileService fileService) {
        super(view, userService);
        this.fileService = fileService;
    }

    @Override
    public void execute() {
        List<User> users = fileService.read();
        userService.setUsers(users);
        view.print("Users loaded from file:");
        for (User i : users) {
            view.printUser(i);
        }
    }
}
