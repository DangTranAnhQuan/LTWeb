package vn.iotstar.dao;

import java.util.List;
import vn.iotstar.entity.User; 

public interface UserDao {
    User findByUsername(String username);
    User findByEmail(String email);
    User findByUsernameAndPassword(String username, String password);
    User findById(Integer id);
    List<User> findAll();
    List<User> search(String kw);
    User create(User e);
    void update(User user);
    void delete(Integer id);
    boolean updatePasswordByEmail(String email, String newPass);
}
