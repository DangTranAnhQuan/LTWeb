package vn.iotstar.dao.impl;

import vn.iotstar.dao.UserDao;
import vn.iotstar.model.User;
import vn.iotstar.controller.DBConnection;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    private static final String BASE_SELECT =
        "SELECT id, email, username, fullname, password, avatar, roleid, phone, createdDate " +
        "FROM [users] ";

    private User mapRow(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setEmail(rs.getString("email"));
        u.setUserName(rs.getString("username"));
        u.setFullName(rs.getString("fullname")); 
        u.setPassWord(rs.getString("password"));
        u.setAvatar(rs.getString("avatar"));
        u.setRoleid(rs.getInt("roleid"));
        u.setPhone(rs.getString("phone"));
        Timestamp ts = rs.getTimestamp("createdDate");
        u.setCreatedDate(ts == null ? null : new java.util.Date(ts.getTime()));
        return u;
    }

    @Override
    public User findByUsername(String username) {
        final String sql = BASE_SELECT + "WHERE username = ?";
        try (Connection cn = new DBConnection().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        final String sql = BASE_SELECT +
            "WHERE username = ? AND RTRIM(LTRIM([password])) = RTRIM(LTRIM(?))";
        try (Connection cn = new DBConnection().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public User get(String username) {
        return findByUsername(username);
    }

    @Override
    public boolean updatePasswordByEmail(String email, String newPass) {
        final String sql = "UPDATE [users] SET [password] = ? WHERE email = ?";
        try (Connection cn = new DBConnection().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, newPass);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
    
    @Override
    public void update(User user) {
        String sql = "UPDATE Users SET fullName=?, phone=?, avatar=? WHERE id=?";
        try (Connection con = new DBConnection().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getPhone());
            ps.setString(3, user.getAvatar());
            ps.setInt(4, user.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
	@Override
	public User findByEmail(String email) {
		return null;
	}
}
