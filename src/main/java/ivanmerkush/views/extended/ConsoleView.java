package ivanmerkush.views.extended;

import ivanmerkush.commands.*;
import ivanmerkush.models.User;
import ivanmerkush.views.View;
import ivanmerkush.services.impl.FileServiceImpl;
import ivanmerkush.services.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.Scanner;

public class ConsoleView extends View {

    private final Scanner scanner;

    public ConsoleView() {
        super();
        scanner = new Scanner(System.in);
        userService = new UserServiceImpl();
        commands = new HashMap<>(7, 1.1f);
        commands.put(1, new GetAllCommand(this, userService));
        commands.put(2, new GetCommand(this, userService));
        commands.put(3, new CreateCommand(this, userService));
        commands.put(4, new EditCommand(this, userService));
        commands.put(5, new DeleteCommand(this, userService));
        commands.put(6, new LoadCommand(this, userService, new FileServiceImpl()));
        commands.put(7, new SaveCommand(this, userService, new FileServiceImpl()));
        commands.put(8, new QuitCommand(this, userService));
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

    public void start() {
        while (true) {
            printCommands();
            int number = Integer.parseInt(scanner.nextLine());
            commands.get(number).execute();
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

    @Override
    public void print(String text) {
        System.out.println(text);
    }

    @Override
    public void printUser(User user) {
        System.out.println(user);
    }
}
