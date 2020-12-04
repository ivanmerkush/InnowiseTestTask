package ivanmerkush.impl;

import ivanmerkush.JsonService;
import ivanmerkush.User;
import ivanmerkush.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private List<User> users;

    private final Scanner scanner;
    private final JsonService jsonService;
    private final Pattern emailPattern;
    private final Pattern phonePattern;
    public UserServiceImpl() {
        scanner = new Scanner(System.in);
        jsonService = new JsonService();
        users = jsonService.read();
        emailPattern = Pattern.compile("^(.+)+@(.+)+\\.(.+)$");
        phonePattern = Pattern.compile("^(375)+\\d{9}");
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public User createUser() {
        String name;
        String surname;
        String email;
        List<String> roles;
        List<String> phoneNumbers;

        System.out.println("Enter name: ");
        name = scanner.nextLine();
        System.out.println("Enter surname: ");
        surname = scanner.nextLine();
        System.out.println("Enter new email: ");
        email = scanner.nextLine();
        Matcher emailMatcher = emailPattern.matcher(email);
        while (!emailMatcher.matches()) {
            System.out.println("Wrong email. Try again: ");
            email = scanner.nextLine();
            emailMatcher = emailPattern.matcher(email);
        }
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
            int number = Integer.parseInt(scanner.nextLine());
            switch (number) {
                case 1:
                    System.out.println("Enter new name:");
                    String newName = scanner.nextLine();
                    user.setName(newName);
                    break;
                case 2:
                    System.out.println("Enter new surname:");
                    String newSurname = scanner.nextLine();
                    user.setSurname(newSurname);
                    break;
                case 3:
                    System.out.println("Enter new email: ");
                    String email = scanner.nextLine();
                    Matcher emailMatcher = emailPattern.matcher(email);
                    while (!emailMatcher.matches()) {
                        System.out.println("Wrong email. Try again:");
                        email = scanner.nextLine();
                        emailMatcher = emailPattern.matcher(email);
                    }
                    user.setEmail(email);
                    break;
                case 4:
                    List<String> roles = inputRoles();
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
        return user;
    }

    @Override
    public void deleteUser() {
        System.out.println("Enter a name:");
        String name = scanner.nextLine();
        System.out.println("Enter a surname:");
        String surname = scanner.nextLine();
        users = users.stream()
                .filter(x -> !x.getName().equals(name) && !x.getSurname().equals(surname))
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
        jsonService.write(users);
    }

    private List<String> inputRoles() {
        List<String> roles = new ArrayList<>();
        System.out.println("Enter new roles. Type \"Enough\" to stop:");
        for(int i = 0; i < 3; i++) {
            String line = scanner.nextLine().toLowerCase();
            if(line.equals("enough")) {
                if(i == 0) {
                    System.out.println("There should be at least 1 role. Type role:");
                }
                else {
                    break;
                }
            }
            else {
                roles.add(line);
            }
        }
        return roles;
    }

    private List<String> inputPhones() {
        List<String> phoneNumbers = new ArrayList<>();
        System.out.println("Enter new phone numbers. Type \"Enough\" to stop:");
        for(int i = 0; i < 3; i++) {
            String line = scanner.nextLine().toLowerCase();
            if(line.equals("enough")) {
                if(i == 0) {
                    System.out.println("There should be at least 1 phone number. Type number:");
                }
                else {
                    break;
                }
            }
            else {
                Matcher phoneMatcher = phonePattern.matcher(line);
                while(!phoneMatcher.matches()) {
                    System.out.println("Wrong phone number. Try again.");
                    line = scanner.nextLine();
                    phoneMatcher = phonePattern.matcher(line);
                }
                phoneNumbers.add(line);
            }
        }
        return phoneNumbers;
    }

}
