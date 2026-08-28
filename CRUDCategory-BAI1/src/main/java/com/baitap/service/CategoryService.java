package com.baitap.service;

import java.util.List;

import com.baitap.model.Category;

public interface CategoryService {
	void insert(Category category);
	void edit(Category category);
	void delete(int id);
	Category get(int id);
	Category get(String name);
	List<Category> getAll();
	List<Category> search(String keyword);
}
