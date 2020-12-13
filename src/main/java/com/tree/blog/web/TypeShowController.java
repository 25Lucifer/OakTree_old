package com.tree.blog.web;

import com.tree.blog.po.Category;
import com.tree.blog.service.BlogService;
import com.tree.blog.service.CategoryService;
import com.tree.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author lucifer
 */
@Controller
public class TypeShowController {


    @Autowired
    CategoryService categoryService;

    @Autowired
    BlogService blogService;


    @GetMapping("/types/{id}")
    public String types(@PathVariable("id") Long id, Model model){

        List<Category> categoryList = categoryService.listCategoryByUsedTimes();
        model.addAttribute("categoryList",categoryList);

        if(id == -1){
            id = categoryList.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setCategoryId(id);
        model.addAttribute("blogList",blogService.listBlogByCagegory(id));
        model.addAttribute("activeTypeId",id);
        return "types";
    }


}
