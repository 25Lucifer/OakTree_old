package com.tree.blog.service;

import com.tree.blog.exception.NotFoundException;
import com.tree.blog.mapper.CategoryMapper;
import com.tree.blog.po.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lucifer
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public Category saveCategory(Category category) {
//        Category category_checkexit = categoryMapper.getCategoryByName(category.getName());
        categoryMapper.saveCategory(category);
        Category result = categoryMapper.getCategoryByName(category.getName());
        return result;
    }

    @Override
    public Category getCategory(Long id) {
        Category category = categoryMapper.getCategory(id);
        return category;
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryMapper.getCategoryByName(name);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryMapper.deleteCategory(id);
    }

    @Override
    public List<Category> listCategoryByUsedTimes() {
        return categoryMapper.listCategoryByUsedTimes();
    }

    @Override
    public List<Category> listCategory() {
        List<Category> categories = categoryMapper.listCategory();
        return categories;
    }

    @Override
    public Category updateCategory(Long id, String name) {
        Category category = categoryMapper.getCategory(id);
        if(null == category){
            throw new NotFoundException("不存在该类型");
        }
        categoryMapper.updateCategory(id,name);
        category = categoryMapper.getCategoryByName(name);
        return category;
    }
}
