package ivanmerkush.views.extended;

import ivanmerkush.commands.*;
import ivanmerkush.models.User;
import ivanmerkush.views.View;
import ivanmerkush.controllers.extended.ConsoleController;
import ivanmerkush.services.impl.FileServiceImpl;
import ivanmerkush.services.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ConsoleView extends View {

    public ConsoleView() {
        super();
        controller = new ConsoleController(FileServiceImpl.getInstance(), UserServiceImpl.getInstance());
        commands = new HashMap<>(7, 1.1f);
        commands.put(1, new GetAllCommand(this, controller));
        commands.put(2, new GetCommand(this, controller));
        commands.put(3, new CreateCommand(this, controller));
        commands.put(4, new EditCommand(this, controller));
        commands.put(5, new DeleteCommand(this, controller));
        commands.put(6, new LoadCommand(this, controller));
        commands.put(7, new SaveCommand(this, controller));
        commands.put(8, new QuitCommand(this, controller));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printCommands();
            int number = Integer.parseInt(scanner.nextLine());
            List<User> users = commands.get(number).execute();
            if (!users.isEmpty()) {
                System.out.println("Result:\n");
                for (User i : users) {
                    System.out.println(i);
                }
            }

        }
    }

    private void printCommands() {
        System.out.println("=================================\n" +
                    "Available actions: " +
                    "\n1)Get all users;" +
                    "\n2)Get user's info;" +
                    "\n3)Create user;" +
                    "\n4)Edit user;" +
                    "\n5)Delete user;" +
                    "\n6)Load users;" +
                    "\n7)Save users;" +
                    "\n8)Quit;"+
                    "\n=================================\n");
    }

}
