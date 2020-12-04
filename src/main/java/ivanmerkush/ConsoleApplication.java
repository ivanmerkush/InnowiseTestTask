package ivanmerkush;

import ivanmerkush.model.User;
import ivanmerkush.service.UserService;
import ivanmerkush.service.impl.UserServiceImpl;

import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {
    private static final UserService userService;

    static {
        userService = new UserServiceImpl();
    }


    public static void main(String[] args) {
        System.out.println("Welcome!");
        Scanner scanner = new Scanner(System.in);
        handlingRequests(scanner);
        System.exit(0);
    }

    private static void handlingRequests(Scanner scanner) {
            sc : while(true) {

            System.out.println("=================================\n" +
                    "Available actions: " +
                    "\n1)Get all users" +
                    ";\n2)Get user's info" +
                    ";\n3)Create user" +
                    ";\n4)Edit user" +
                    ";\n5)Delete user" +
                    ";\n6)Save and Quit;" +
                    "\n=================================\n");
            int requestNumber = Integer.parseInt(scanner.nextLine());

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
                    checkResult(user);
                    break;
                case 3:
                    user = userService.createUser();
                    checkResult(user);
                    break;
                case 4:
                    user = userService.editUser();
                    checkResult(user);
                    break;
                case 5:
                    userService.deleteUser();
                    System.out.println("The user was deleted.");
                    break;
                case 6:
                    System.out.println("Saving users to file.");
                    userService.saveUsers();
                    scanner.close();
                    System.out.println("Closing. Bye");
                    break sc;
                default:
                    System.out.println("Wrong request. Try again.");
            }
        }
    }

    private static void checkResult(User user) {
        if (user == null) {
            System.out.println("This user doesn't exist");
        } else {
            System.out.println("Here is user's info:\n"
                    + user);
        }
    }

}
