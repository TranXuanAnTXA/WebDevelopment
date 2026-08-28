package com.baitap.dao;

import java.util.List;

import com.baitap.model.Category;

public interface CategoryDao {

    void insert(Category category);

    void edit(Category category);

    void delete(int id);

    Category get(int id);

    List<Category> getAll();
}