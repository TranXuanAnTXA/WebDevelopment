package com.baitap.serviceimpl;

import java.util.List;

import com.baitap.dao.CategoryDao;
import com.baitap.daoimpl.CategoryDaoImpl;
import com.baitap.model.Category;
import com.baitap.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao cateDao =
            new CategoryDaoImpl();

    @Override
    public void insert(Category category) {

        cateDao.insert(category);
    }

    @Override
    public void edit(Category category) {

        cateDao.edit(category);
    }

    @Override
    public void delete(int id) {

        cateDao.delete(id);
    }

    @Override
    public Category get(int id) {

        return cateDao.get(id);
    }

    @Override
    public List<Category> getAll() {

        return cateDao.getAll();
    }
}