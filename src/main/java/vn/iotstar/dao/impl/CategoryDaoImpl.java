package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.controller.DBConnection;
import vn.iotstar.dao.CategoryDao;
import vn.iotstar.model.Category;

public class CategoryDaoImpl extends DBConnection implements CategoryDao {

	@Override
	public void insert(Category category) {
		String sql = "INSERT INTO categories (cate_name, icon, user_id) VALUES (?, ?, ?)";
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
		String sql = "UPDATE categories SET cate_name = ?, icon = ? WHERE cate_id = ?";
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
		String sql = "DELETE FROM categories WHERE cate_id = ?";
		try (Connection con = super.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Category get(int id) {
		String sql = "SELECT cate_id, cate_name, icon, user_id FROM categories WHERE cate_id = ?";
		try (Connection con = super.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Category c = new Category();
					c.setCateid(rs.getInt("cate_id"));
					c.setCatename(rs.getString("cate_name"));
					c.setIcon(rs.getString("icon"));
					c.setUserId(rs.getInt("user_id"));
					return c;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Category get(String name) {
		String sql = "SELECT cate_id, cate_name, icon, user_id FROM categories WHERE cate_name = ?";
		try (Connection con = super.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, name);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Category c = new Category();
					c.setCateid(rs.getInt("cate_id"));
					c.setCatename(rs.getString("cate_name"));
					c.setIcon(rs.getString("icon"));
					c.setUserId(rs.getInt("user_id"));
					return c;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Category> getAll() {
		List<Category> list = new ArrayList<>();
		String sql = "SELECT cate_id, cate_name, icon, user_id FROM categories ORDER BY cate_id";
		try (Connection con = super.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Category c = new Category();
				c.setCateid(rs.getInt("cate_id"));
				c.setCatename(rs.getString("cate_name"));
				c.setIcon(rs.getString("icon"));
				c.setUserId(rs.getInt("user_id"));
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Category> search(String keyword) {
		List<Category> list = new ArrayList<>();
		String sql = """
				SELECT cate_id, cate_name, icon, user_id
				FROM categories
				WHERE cate_name LIKE ? OR icon LIKE ?
				ORDER BY cate_id
				""";
		try (Connection con = super.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			String like = "%" + keyword + "%";
			ps.setString(1, like);
			ps.setString(2, like);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Category c = new Category();
					c.setCateid(rs.getInt("cate_id"));
					c.setCatename(rs.getString("cate_name"));
					c.setIcon(rs.getString("icon"));
					c.setUserId(rs.getInt("user_id"));
					list.add(c);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Category> getByUser(int userId) {
		List<Category> list = new ArrayList<>();
		String sql = "SELECT cate_id, cate_name, icon, user_id FROM categories WHERE user_id = ? ORDER BY cate_id";
		try (Connection con = super.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Category c = new Category();
					c.setCateid(rs.getInt("cate_id"));
					c.setCatename(rs.getString("cate_name"));
					c.setIcon(rs.getString("icon"));
					c.setUserId(rs.getInt("user_id"));
					list.add(c);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
