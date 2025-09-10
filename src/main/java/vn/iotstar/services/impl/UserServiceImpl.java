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
	
	@Override
	public User getByEmail(String email) {
	    if (email == null || email.isBlank()) return null;
	    return userDao.findByEmail(email.trim());
	}

	@Override
	public boolean updatePasswordByEmail(String email, String newPassword) {
	    if (email == null || newPassword == null 
	            || email.isBlank() || newPassword.isBlank()) {
	        return false;
	    }
	    return userDao.updatePasswordByEmail(email.trim(), newPassword.trim());
	}
	
	@Override
	public void update(User user) {
	    userDao.update(user);   
	}


}
