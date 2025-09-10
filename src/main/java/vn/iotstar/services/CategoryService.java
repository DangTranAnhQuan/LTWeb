package vn.iotstar.services;

import java.util.List;
import vn.iotstar.model.Category;

public interface CategoryService {
	void insert(Category category);

	void edit(Category category);

	void update(Category category);

	void delete(int id);

	Category get(int id);

	Category get(String name);

	Category findById(int id);

	List<Category> getAll();

	List<Category> search(String keyword);

	List<Category> getByUser(int userId);
}
