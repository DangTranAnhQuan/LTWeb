package vn.iotstar.dao.impl;

import vn.iotstar.dao.UserDao;
import vn.iotstar.model.User;
import vn.iotstar.controller.DBConnection;

import java.sql.*;
import java.util.Date;

public class UserDaoImpl implements UserDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	private static final String SQL_GET = "SELECT id, email, username, fullname, password, avatar, roleid, phone, createdDate "
			+ "FROM [users] WHERE username = ?";

	@Override
	public User get(String username) {
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(SQL_GET);
			ps.setString(1, username);
			rs = ps.executeQuery();

			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("username"));
				user.setFullName(rs.getString("fullname"));
				user.setPassWord(rs.getString("password"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleid(rs.getInt("roleid"));
				user.setPhone(rs.getString("phone"));

				Timestamp ts = rs.getTimestamp("createdDate");
				user.setCreatedDate(ts != null ? new Date(ts.getTime()) : null);

				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception ignore) {
			}
		}
		return null;
	}

	@Override
	public User getByEmail(String email) {
		String sql = "SELECT * FROM [users] WHERE email = ?";
		try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					User u = new User();
					u.setId(rs.getInt("id"));
					u.setEmail(rs.getString("email"));
					u.setUserName(rs.getString("username"));
					u.setFullName(rs.getString("fullName"));
					u.setPassWord(rs.getString("password"));
					u.setAvatar(rs.getString("avatar"));
					u.setRoleid(rs.getInt("roleid"));
					u.setPhone(rs.getString("phone"));
					u.setCreatedDate(rs.getDate("createdDate"));
					return u;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updatePasswordByEmail(String email, String newPassword) {
		String sql = "UPDATE [users] SET [password] = ? WHERE email = ?";
		try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, newPassword);
			ps.setString(2, email);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		String sql = "SELECT id, email, username, fullname, password, avatar, roleid, phone, createdDate "
				+ "FROM dbo.users " + "WHERE username = ? AND RTRIM(LTRIM(password)) = RTRIM(LTRIM(?))";
		try (Connection cn = new DBConnection().getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
			ps.setString(1, username);
			ps.setString(2, password);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					User u = new User();
					u.setId(rs.getInt("id"));
					u.setEmail(rs.getString("email"));
					u.setUserName(rs.getString("username"));
					u.setFullName(rs.getString("fullname"));
					u.setPassWord(rs.getString("password")); // hoặc không cần set
					u.setAvatar(rs.getString("avatar"));
					u.setRoleid(rs.getInt("roleid"));
					u.setPhone(rs.getString("phone"));
					u.setCreatedDate(rs.getDate("createdDate"));
					return u;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findByUsername(String username) {
		String sql = "SELECT id, email, username, fullname, password, avatar, roleid, phone, createdDate "
				+ "FROM dbo.users WHERE username = ?";
		try (Connection cn = new DBConnection().getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					User u = new User();
					u.setId(rs.getInt("id"));
					u.setEmail(rs.getString("email"));
					u.setUserName(rs.getString("username"));
					u.setFullName(rs.getString("fullname"));
					u.setPassWord(rs.getString("password"));
					u.setAvatar(rs.getString("avatar"));
					u.setRoleid(rs.getInt("roleid"));
					u.setPhone(rs.getString("phone"));
					u.setCreatedDate(rs.getDate("createdDate"));
					return u;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
