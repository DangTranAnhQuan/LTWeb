package vn.iotstar.dao.impl;

import vn.iotstar.dao.CategoryDao;
import vn.iotstar.model.Category;
import vn.iotstar.controller.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDaoImpl extends DBConnection implements CategoryDao {

	private Category map(ResultSet rs) throws Exception {
		Category c = new Category();
		c.setCateid(rs.getInt("cate_id"));
		c.setCatename(rs.getString("cate_name"));
		c.setIcon(rs.getString("icon"));
		try {
			c.setUserId(rs.getInt("user_id"));
		} catch (Exception ignored) {
		}
		return c;
	}

	@Override
	public void insert(Category category) {
		final String sql = "INSERT INTO categories(cate_name, icon, user_id) VALUES (?, ?, ?)";
		try (Connection con = super.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, category.getCatename());
			ps.setString(2, category.getIcon());
			ps.setInt(3, category.getUserId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void edit(Category category) {
		final String sql = "UPDATE categories SET cate_name = ?, icon = ? WHERE cate_id = ?";
		try (Connection con = super.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, category.getCatename());
			ps.setString(2, category.getIcon());
			ps.setInt(3, category.getCateid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		final String sql = "DELETE FROM categories WHERE cate_id = ?";
		try (Connection con = super.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Category get(int id) {
		final String sql = "SELECT cate_id, cate_name, icon, user_id FROM categories WHERE cate_id = ?";
		try (Connection con = super.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return map(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Category get(String name) {
		final String sql = "SELECT cate_id, cate_name, icon, user_id FROM categories WHERE cate_name = ?";
		try (Connection con = super.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, name);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return map(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Category> getAll() {
		List<Category> list = new ArrayList<>();
		final String sql = "SELECT cate_id, cate_name, icon, user_id FROM categories ORDER BY cate_id";
		try (Connection con = super.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next())
				list.add(map(rs));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Category> search(String keyword) {
	    List<Category> list = new ArrayList<>();
	    final String sql =
	        "SELECT cate_id, cate_name, icon, user_id " +
	        "FROM categories " +
	        "WHERE cate_name COLLATE Vietnamese_CI_AI LIKE ? " +
	        "ORDER BY cate_id";

	    String kw = (keyword == null) ? "" : keyword.trim();
	    try (Connection con = super.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, "%" + kw + "%");
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) list.add(map(rs));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	@Override
	public List<Category> getByUser(int userId) {
		List<Category> list = new ArrayList<>();
		final String sql = "SELECT cate_id, cate_name, icon, user_id FROM categories "
				+ "WHERE user_id = ? ORDER BY cate_id";
		try (Connection con = super.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next())
					list.add(map(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Optional<Category> findById(int id) {
		return Optional.ofNullable(get(id));
	}
}
