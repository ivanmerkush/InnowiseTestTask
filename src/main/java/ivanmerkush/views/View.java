package ivanmerkush.views;

import ivanmerkush.commands.Command;
import ivanmerkush.services.UserService;

import java.util.Map;

public abstract class View implements Printer{
    protected Map<Integer, Command> commands;
    protected UserService userService;

    public View() {

    }

    public abstract String read();
}
