package vn.iotstar.services.impl;

import java.util.List;

import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.entity.User;
import vn.iotstar.services.UserService;

public class UserServiceImpl implements UserService {

    private final UserDao dao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        return dao.findByUsernameAndPassword(username, password);
    }

    @Override
    public User findByUsername(String username) {
        return dao.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return dao.findByEmail(email);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return dao.findByUsernameAndPassword(username, password);
    }

    @Override
    public User findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public List<User> search(String kw) {
        return dao.search(kw);
    }

    @Override
    public User create(User e) {
        return dao.create(e);
    }

    @Override
    public void update(User user) {
        dao.update(user);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

    @Override
    public boolean updatePasswordByEmail(String email, String newPass) {
        return dao.updatePasswordByEmail(email, newPass);
    }

    @Override
    public User save(User u) {
        if (u.getId() == null) {
            return dao.create(u);
        }
        dao.update(u);
        return u;
    }
}
