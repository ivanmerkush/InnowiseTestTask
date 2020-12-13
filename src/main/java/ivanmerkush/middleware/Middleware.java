package ivanmerkush.middleware;

import java.util.List;

public abstract class Middleware {
    private Middleware next;

    public Middleware linkWith(Middleware next) {
        this.next = next;
        return next;
    }

    public abstract boolean check(String email, List<String> phones);

    protected boolean checkNext(String email, List<String> phones) {
        if (next == null) {
            return true;
        }
        return next.check(email, phones);
    }

}
