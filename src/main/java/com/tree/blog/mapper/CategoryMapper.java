package com.tree.blog.mapper;

import com.tree.blog.po.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lucifer
 */
@Mapper
@Repository
public interface CategoryMapper {

    void saveCategory(Category category);

    Category getCategory(Long id);

    Category getCategoryByName(String name);

    List<Category> listCategory();

    void updateCategory(Long id,String name);

    void deleteCategory(Long id);

    List<Category> listCategoryByUsedTimes();
}
