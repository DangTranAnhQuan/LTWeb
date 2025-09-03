package vn.iotstar.dao;

import vn.iotstar.model.User;

public interface UserDao {
	User findByUsername(String username);

	User findByUsernameAndPassword(String username, String password);

	User get(String username);

	User getByEmail(String email);

	boolean updatePasswordByEmail(String email, String newPass);
}
