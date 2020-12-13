package ivanmerkush.commands;

import ivanmerkush.services.UserService;
import ivanmerkush.views.View;

public class QuitCommand extends Command{

    public QuitCommand(View view, UserService userService) {
        super(view, userService);
    }

    @Override
    public void execute() {
        view.print("Closing application. Bye!");
        System.exit(0);
    }
}