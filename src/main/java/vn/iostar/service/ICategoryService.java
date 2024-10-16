package vn.iostar.service;

import java.util.List;
import vn.iostar.entity.Category;

public interface ICategoryService {

	void insert(Category category);

	int count();

	List<Category> findAll(int page, int pageSize);

	List<Category> searchByName(String keyword);

	List<Category> findAll();

	Category findById(int id);

	void delete(int id);

	void update(Category category);

	Category findByCategoryname(String name);

	
}