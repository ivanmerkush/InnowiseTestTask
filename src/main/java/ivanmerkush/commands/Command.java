package ivanmerkush.commands;

import ivanmerkush.models.Role;
import ivanmerkush.models.Roles;
import ivanmerkush.services.UserService;
import ivanmerkush.views.View;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Command {
    public View view;
    public UserService userService;
    public final Pattern emailPattern = Pattern.compile("^(.+)+@(.+)+\\.(.+)$");
    public final Pattern phonePattern = Pattern.compile("^(375)+\\d{9}");

    public Command(View view, UserService userService) {
        this.view = view;
        this.userService = userService;
    }

    protected String[] inputNameSurname() {
        String[] result = new String[2];
        view.print("Enter name:");
        result[0] = view.read();
        view.print("Enter surname:");
        result[1] = view.read();
        return result;
    }


    protected String addEmail() {
        view.print("Enter new email:");
        Matcher emailMatcher = emailPattern.matcher(view.read());
        while (!emailMatcher.matches()) {
            view.print("Wrong email. Try again:");
            emailMatcher = emailPattern.matcher(view.read());
        }
        return emailMatcher.group();
    }

    protected List<String> addPhones() {
        List<String> phoneNumbers = new ArrayList<>();
        view.print("Enter new phone numbers. Type \"Enough\" to stop:");
        for(int i = 0; i < 3;) {
            String line = view.read().toLowerCase();
            if(line.equals("enough")) {
                if(i == 0) {
                    view.print("There should be at least 1 value. Type again:");
                }
                else {
                    break;
                }
            }
            else {
                Matcher phoneMatcher = phonePattern.matcher(line);
                if(!phoneMatcher.matches()) {
                    view.print("Wrong phone number. Try again.");
                }
                else {
                    phoneNumbers.add(line);
                    i++;
                }
            }
        }
        return phoneNumbers;
    }

    protected Roles addRoles() {
        Roles roles = new Roles();
        view.print("Choose roles. Type \"Enough\" to stop:");
        view.print("1)USER;\n" +
                "2)CUSTOMER;\n" +
                "3)ADMIN;\n"+
                "4)PROVIDER;\n" +
                "5)SUPER_ADMIN;\n");
        for(int i = 0; i < 2;) {
            String line = view.read().toLowerCase();
            if(line.equals("enough")) {
                if(i == 0) {
                    view.print("There should be at least 1 value. Type again:");
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
        Role[] roles1 = Role.values();
        switch(number) {
            case 1:
            case 2:
                if (roles.getFirstRole() != null) {
                    view.print("First level role is already set.");
                    return false;
                }
                roles.setFirstRole(roles1[number-1]);
                view.print("First level role was set.");
                break;
            case 3:
            case 4:
                if (roles.getSecondRole() != null) {
                    view.print("Second level role is already set.");
                    return false;
                }
                roles.setSecondRole(roles1[number-1]);
                view.print("Second level role was set.");
                break;
            case 5:
                if (roles.getSecondRole() != null || roles.getFirstRole() != null) {
                    view.print("User has others level roles");
                    return false;
                }
                roles.setThirdRole(Role.SUPER_ADMIN);
                view.print("Third level role was set.");
                break;
            default:
                view.print("Wrong number.");
                break;
        }
        return  true;
    }

    public abstract void execute();
}
