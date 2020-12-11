package ivanmerkush.service.impl;

import ivanmerkush.model.Role;
import ivanmerkush.model.Roles;
import ivanmerkush.model.User;
import ivanmerkush.service.FileService;
import ivanmerkush.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private List<User> users;
    private FileService fileService;

    private final Scanner scanner;
    private final Pattern emailPattern;
    private final Pattern phonePattern;
    private UserServiceImpl() {
        users = new ArrayList<>();
        scanner = new Scanner(System.in);
        emailPattern = Pattern.compile("^(.+)+@(.+)+\\.(.+)$");
        phonePattern = Pattern.compile("^(375)+\\d{9}");
    }



    private static class UserServiceHolder {
        private static final UserServiceImpl instance = new UserServiceImpl();

    }

    public static UserServiceImpl getInstance() {
        return UserServiceHolder.instance;
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public User createUser() {
        String name;
        String surname;
        String email;
        Roles roles;
        List<String> phoneNumbers;

        System.out.println("Enter name:");
        name = scanner.nextLine();
        System.out.println("Enter surname:");
        surname = scanner.nextLine();
        System.out.println("Enter new email:");
        email = inputEmail();
        roles = inputRoles();
        phoneNumbers = inputPhones();
        User user = new User(name, surname, email, roles,phoneNumbers);
        users.add(user);
        return user;
    }

    @Override
    public User editUser() {
        System.out.println("Enter a name:");
        String name = scanner.nextLine();
        System.out.println("Enter a surname:");
        String surname = scanner.nextLine();
        User user = users.stream()
                .filter(x -> x.getName().equals(name) && x.getSurname().equals(surname))
                .findFirst().orElse(null);
        if (user != null) {
            System.out.println(user.toString());
            System.out.println("What would you like to change?");
            changingValues(user);
        }
        return user;
    }

    @Override
    public void deleteUser() {
        System.out.println("Enter a name:");
        String name = scanner.nextLine();
        System.out.println("Enter a surname:");
        String surname = scanner.nextLine();
        users = users.stream()
                .filter(x -> !(x.getName().equals(name) && x.getSurname().equals(surname)))
                .collect(Collectors.toList());
    }

    @Override
    public User getUserInfo() {
        System.out.println("Enter a name:");
        String name = scanner.nextLine();
        System.out.println("Enter a surname:");
        String surname = scanner.nextLine();
        return users.stream()
                .filter(x -> x.getName().equals(name) && x.getSurname().equals(surname))
                .findFirst().orElse(null);
    }

    @Override
    public List<String> getAllUsers() {
        List<String> names = new ArrayList<>();
        users.forEach(x -> names.add(x.getName().concat(" ").concat(x.getSurname())));
        return names;
    }

    @Override
    public void saveUsers() {
        fileService.write(users);
    }

    @Override
    public void loadUsers() {
        users.addAll(fileService.read());
    }

    @Override
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }


    private void changingValues(User user) {
        int number = Integer.parseInt(scanner.nextLine());
        switch (number) {
            case 1:
                System.out.println("Enter new name:");
                String name = scanner.nextLine();
                user.setName(name);
                break;
            case 2:
                System.out.println("Enter new surname:");
                String surname = scanner.nextLine();
                user.setSurname(surname);
                break;
            case 3:
                System.out.println("Enter new email: ");
                user.setEmail(inputEmail());
                break;
            case 4:
                Roles roles = inputRoles();
                user.setRoles(roles);
                break;
            case 5:
                List<String> phones = inputPhones();
                user.setPhoneNumbers(phones);
                break;
            default:
                System.out.println("Nothing will change.");
                break;
        }
    }

    private String inputEmail() {
        Matcher emailMatcher = emailPattern.matcher(scanner.nextLine());
        while (!emailMatcher.matches()) {
            System.out.println("Wrong email. Try again:");
            emailMatcher = emailPattern.matcher(scanner.nextLine());
        }
        return emailMatcher.group();
    }

    private Roles inputRoles() {
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

    private List<String> inputPhones() {
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



}
