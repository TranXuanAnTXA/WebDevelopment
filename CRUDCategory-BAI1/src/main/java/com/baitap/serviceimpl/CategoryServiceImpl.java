package com.baitap.serviceimpl;

import java.io.File;
import java.util.List;

import com.baitap.daoimpl.CategoryDaoImpl;
import com.baitap.dao.CategoryDao;
import com.baitap.model.Category;
import com.baitap.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

    CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public void insert(Category category) {
        categoryDao.insert(category);
    }

    @Override
    public void edit(Category newCategory) {

        Category oldCategory = categoryDao.get(newCategory.getId());

        if (oldCategory != null) {

            oldCategory.setName(newCategory.getName());

            if (newCategory.getIcon() != null) {

                // Xóa ảnh cũ
                String fileName = oldCategory.getIcon();

                final String dir = "E:\\upload";

                File file = new File(dir + "\\category\\" + fileName);

                if (file.exists()) {
                    file.delete();
                }

                // Gán ảnh mới
                oldCategory.setIcon(newCategory.getIcon());
            }

            categoryDao.edit(oldCategory);
        }
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
    
}