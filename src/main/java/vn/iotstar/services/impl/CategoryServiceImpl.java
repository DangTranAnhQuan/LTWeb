package vn.iotstar.services.impl;

import java.io.File;
import java.util.List;

import vn.iotstar.dao.CategoryDao;
import vn.iotstar.dao.impl.CategoryDaoImpl;
import vn.iotstar.model.Category;
import vn.iotstar.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	private final CategoryDao categoryDao = new CategoryDaoImpl();

	@Override
	public void insert(Category category) {
		categoryDao.insert(category);
	}

	@Override
	public void delete(int id) {
		categoryDao.delete(id);
	}

	@Override
	public Category get(int id) {
		return categoryDao.get(id);
	}

	@Override
	public void edit(Category newCategory) {
		Category oldCategory = categoryDao.get(newCategory.getCateid());
		if (oldCategory == null)
			return;

		oldCategory.setCatename(newCategory.getCatename());

		if (newCategory.getIcon() != null && !newCategory.getIcon().isBlank()) {
			String oldIcon = oldCategory.getIcon();
			if (oldIcon != null && !oldIcon.isBlank()) {
				File file = new File("E:\\upload\\category", oldIcon);
				if (file.exists())
					file.delete();
			}
			oldCategory.setIcon(newCategory.getIcon());
		}

		categoryDao.edit(oldCategory);
	}

	@Override
	public Category get(String name) {
		return categoryDao.get(name);
	}

	@Override
	public List<Category> getAll() {
		return categoryDao.getAll();
	}

	@Override
	public List<Category> search(String keyword) {
		return categoryDao.search(keyword);
	}

	@Override
	public List<Category> getByUser(int userId) {
		return categoryDao.getByUser(userId);
	}
}
