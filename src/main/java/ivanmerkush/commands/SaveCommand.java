package ivanmerkush.commands;

import ivanmerkush.services.FileService;
import ivanmerkush.services.UserService;
import ivanmerkush.views.View;


public class SaveCommand extends Command{

    private final FileService fileService;
    public SaveCommand(View view, UserService userService, FileService fileService) {
        super(view, userService);
        this.fileService = fileService;
    }

    @Override
    public void execute() {
        view.print("Users saved to a file");
        fileService.write(userService.getAllUsers());
    }
}
