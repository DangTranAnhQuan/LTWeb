package vn.iotstar.services.impl;

import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.model.User;
import vn.iotstar.services.UserService;

public class UserServiceImpl implements UserService {

	private final UserDao userDao = new UserDaoImpl();

	@Override
	public User login(String username, String password) {
		if (username == null || password == null)
			return null;
		return userDao.findByUsernameAndPassword(username.trim(), password.trim());
	}

	@Override
	public User get(String username) {
		return userDao.findByUsername(username);
	}
}
