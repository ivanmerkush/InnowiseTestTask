package ivanmerkush.models;

import java.io.Serializable;

public class Roles implements Serializable {
    private Role firstRole;
    private Role secondRole;
    private Role thirdRole;

    public Role getFirstRole() {
        return firstRole;
    }

    public void setFirstRole(Role firstRole) {
        if(this.firstRole == null) {
            this.firstRole = firstRole;
        }
    }

    public Role getSecondRole() {
        return secondRole;
    }

    public void setSecondRole(Role secondRole) {
        if(this.secondRole == null) {
            this.secondRole = secondRole;
        }
    }

    public Role getThirdRole() {
        return thirdRole;
    }

    public void setThirdRole(Role thirdRole) {
        if(this.firstRole == null && this.secondRole == null) {
            this.thirdRole = thirdRole;
        }
    }

    @Override
    public String toString() {
        return "Roles {" +
                "First role - " + (firstRole == null ? "none" : firstRole) +
                "; Second role - " + (secondRole == null ? "none" : secondRole) +
                "; Third role - " + (thirdRole == null ? "none" : thirdRole) +
                '}';
    }
}
