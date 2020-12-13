package ivanmerkush.middleware;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailMiddleWare extends Middleware{
                    
    @Override
    public boolean check(String email, List<String> phones) {
        Pattern pattern = Pattern.compile("^(.+)+@(.+)+\\.(.+)$");
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()) {
            return false;
        }
        return checkNext(email, phones);
    }
}
