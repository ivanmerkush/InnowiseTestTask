package ivanmerkush;

import java.util.List;

public interface UserService {
    User createUser();
    User editUser();
    void deleteUser();
    User getUserInfo();
    List<String> getAllUsers();
    void saveUsers();
}
