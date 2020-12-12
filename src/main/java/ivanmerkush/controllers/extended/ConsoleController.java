package ivanmerkush.controllers.extended;

import ivanmerkush.controllers.Controller;
import ivanmerkush.models.Role;
import ivanmerkush.models.Roles;
import ivanmerkush.models.User;
import ivanmerkush.services.FileService;
import ivanmerkush.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ConsoleController extends Controller {
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleController(FileService fileService, UserService userService) {

        super(fileService, userService);
    }

    @Override
    public List<User> findAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public User findUser() {
        String[] name = inputNameSurname();
        return userService.getUserByNameAndSurname(name[0], name[1]);
    }

    @Override
    public User createUser() {
        String[] name = inputNameSurname();
        String email = addEmail();
        Roles roles = addRoles();
        List<String> phones = addPhones();
        System.out.println("User created:");
        return userService.createUser(name[0], name[1], email, roles, phones);
    }

    @Override
    public User editUser() {
        User user = findUser();
        int index = userService.getAllUsers().indexOf(user);
        System.out.println(user);
        System.out.println("What would you like to change?");
        int number = Integer.parseInt(scanner.nextLine());
        return editValues(number, user, index);
    }

    @Override
    public void deleteUser() {
        String[] name = inputNameSurname();
        userService.deleteUser(name[0], name[1]);
        System.out.println("User deleted");
    }

    @Override
    public List<User> loadUsers() {
        userService.setUsers(fileService.read());
        return findAllUsers();
    }

    @Override
    public void saveUsers() {
        fileService.write(userService.getAllUsers());
    }

    private String[] inputNameSurname() {
        String[] result = new String[2];
        System.out.println("Enter name:");
        result[0] = scanner.nextLine();
        System.out.println("Enter surname:");
        result[1] = scanner.nextLine();
        return result;
    }

    public User editValues(int number, User user,int index) {

        switch (number) {
            case 1:
                System.out.println("Enter new name:");
                user.setName(scanner.nextLine());
                break;
            case 2:
                System.out.println("Enter new surname:");
                String surname = scanner.nextLine();
                user.setSurname(surname);
                break;
            case 3:
                System.out.println("Enter new email: ");
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
                System.out.println("Nothing will change.");
                return user;
        }
        userService.editUser(user, index);
        System.out.println("User edited.");
        return user;
    }

    public Roles addRoles() {
        Roles roles = new Roles();
        System.out.println("Choose roles. Type \"Enough\" to stop:");
        System.out.println("1)USER;\n" +
                "2)CUSTOMER;\n" +
                "3)ADMIN;\n"+
                "4)PROVIDER;\n" +
                "5)SUPER_ADMIN;\n");
        for(int i = 0; i < 2;) {
            String line = scanner.nextLine().toLowerCase();
            if(line.equals("enough")) {
                if(i == 0) {
                    System.out.println("There should be at least 1 value. Type again:");
                }
                else {
                    break;
                }
            }
            else {
                if(chooseRole(Integer.parseInt(line), roles)) {
                    i++;
                    if(roles.getThirdRole() != null) {
                        break;
                    }
                }

            }
        }
        return roles;
    }

    private boolean chooseRole(int number, Roles roles) {
        switch(number) {
            case 1:
                if(roles.getFirstRole() == null) {
                    roles.setFirstRole(Role.USER);
                    return true;
                }
                else {
                    System.out.println("This user already has first role");
                    return false;
                }
            case 2:
                if(roles.getFirstRole() == null) {
                    roles.setFirstRole(Role.CUSTOMER);
                    return true;
                }
                else {
                    System.out.println("This user already has first role");
                    return false;
                }
            case 3:
                if(roles.getSecondRole() == null) {
                    roles.setSecondRole(Role.ADMIN);
                    return true;
                }
                else {
                    System.out.println("This user already has second role");
                    return false;
                }
            case 4:
                if(roles.getSecondRole() == null) {
                    roles.setSecondRole(Role.PROVIDER);
                    return true;
                }
                else {
                    System.out.println("This user already has second role");
                    return false;
                }
            case 5:
                if(roles.getSecondRole() == null && roles.getFirstRole() == null) {
                    roles.setThirdRole(Role.SUPER_ADMIN);
                    return true;
                }
                else {
                    System.out.println("This user already has other roles");
                    return false;
                }
            default:
                System.out.println("Wrong number.");
                return false;
        }
    }

    private List<String> addPhones() {
        List<String> phoneNumbers = new ArrayList<>();
        System.out.println("Enter new phone numbers. Type \"Enough\" to stop:");
        for(int i = 0; i < 3;) {
            String line = scanner.nextLine().toLowerCase();
            if(line.equals("enough")) {
                if(i == 0) {
                    System.out.println("There should be at least 1 value. Type again:");
                }
                else {
                    break;
                }
            }
            else {
                Matcher phoneMatcher = phonePattern.matcher(line);
                if(!phoneMatcher.matches()) {
                    System.out.println("Wrong phone number. Try again.");
                }
                else {
                    phoneNumbers.add(line);
                    i++;
                }
            }
        }
        return phoneNumbers;
    }

    private String addEmail() {
        System.out.println("Enter new email:");
        Matcher emailMatcher = emailPattern.matcher(scanner.nextLine());
        while (!emailMatcher.matches()) {
            System.out.println("Wrong email. Try again:");
            emailMatcher = emailPattern.matcher(scanner.nextLine());
        }
        return emailMatcher.group();
    }


}

