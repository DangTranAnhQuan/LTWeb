package vn.iotstar.services;

import java.util.List;
import vn.iotstar.entity.User; 

public interface UserService {
    User login(String username, String password);

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

    User save(User u);
}
