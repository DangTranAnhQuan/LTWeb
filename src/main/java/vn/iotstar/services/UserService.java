package vn.iotstar.services;

import vn.iotstar.model.User;

public interface UserService {
    User login(String username, String password);
    User get(String username);
    User getByEmail(String email);
    boolean updatePasswordByEmail(String email, String newPassword);
    
    void update(User user);
}
