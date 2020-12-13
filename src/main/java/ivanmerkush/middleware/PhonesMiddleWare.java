package ivanmerkush.middleware;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhonesMiddleWare extends Middleware {

    @Override
    public boolean check(String email, List<String> phones) {
        Pattern phonePattern = Pattern.compile("^(375)+\\d{9}");
        for(String i : phones) {
            Matcher matcher = phonePattern.matcher(i);
            if(!matcher.matches()) {
                return false;
            }
        }
        return checkNext(email, phones);
    }
}
