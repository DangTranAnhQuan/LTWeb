package vn.iotstar.dao;

import vn.iotstar.model.User;

public interface UserDao {
    User findByUsername(String username);
    User findByEmail(String email);
    User findByUsernameAndPassword(String username, String password);

    boolean updatePasswordByEmail(String email, String newPass);
    void update(User user);
}
