package ivanmerkush;

import ivanmerkush.impl.UserServiceImpl;

import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {
    private static final UserService userService;

    static {
        userService = new UserServiceImpl();
    }


    public static void main(String[] args) {
        int requestNumber;
        Scanner scanner = new Scanner(System.in);
        boolean isClosed = false;
        System.out.println("Welcome!");
        while(!isClosed) {
            System.out.println("=================================\n" +
                    "Available actions: " +
                    "\n1)Get all users" +
                    ";\n2)Get user's info" +
                    ";\n3)Create user" +
                    ";\n4)Edit user" +
                    ";\n5)Delete user" +
                    ";\n6)Save and Quit;" +
                    "\n=================================\n");
            requestNumber = scanner.nextInt();
            switch (requestNumber) {
                case 1:
                    System.out.println("All user's names and surnames:");
                    List<String> users = userService.getAllUsers();
                    for(int i = 0;i < users.size();i++) {
                        System.out.println(i+1 + ")" + users.get(i));
                    }
                    break;
                case 2:
                    User user = userService.getUserInfo();
                    if (user == null) {
                        System.out.println("This user doesn't exist");
                    } else {
                        System.out.println("Here is user's info:\n"
                                + user);
                    }
                    break;
                case 3:
                    user = userService.createUser();
                    System.out.println("The new user was created:\n"
                            + user);
                    break;
                case 4:
                    user = userService.editUser();
                    if (user == null) {
                        System.out.println("This user doesn't exist");
                    } else {
                        System.out.println("The user was edited:\n"
                                + user);
                    }
                    break;
                case 5:
                    userService.deleteUser();
                    System.out.println("The user was deleted.");
                    break;
                case 6:
                    isClosed = true;
                    scanner.close();
                    System.out.println("Saving users to file.");
                    userService.saveUsers();
                    System.out.println("Closing. Bye");
                    break;
                default:
                    System.out.println("Wrong request. Try again.");
                    break;
            }
        }
        System.exit(0);
    }

}
