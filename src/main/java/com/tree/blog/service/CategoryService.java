package com.tree.blog.service;

import com.tree.blog.po.Category;

import java.util.List;

/**
 * @author lucifer
 */
public interface CategoryService{

    Category saveCategory(Category category);

    Category getCategory(Long id);

    Category getCategoryByName(String name);


    List<Category> listCategory();

    Category updateCategory(Long id,String name);

    void deleteCategory(Long id);

    List<Category> listCategoryByUsedTimes();

}
