package com.tree.blog.web;

import com.tree.blog.exception.NotFoundException;
import com.tree.blog.po.Blog;
import com.tree.blog.po.Category;
import com.tree.blog.po.Comment;
import com.tree.blog.po.Tag;
import com.tree.blog.service.BlogService;
import com.tree.blog.service.CategoryService;
import com.tree.blog.service.TagService;
import com.tree.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lucifer
 */
@Controller
public class indexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(Model model){
        List<Blog> blogList = blogService.listBlog();
        model.addAttribute("blogList",blogList);

        List<Category> categoryList = categoryService.listCategoryByUsedTimes();
        model.addAttribute("categoryList",categoryList);


        List<Tag> tagList = tagService.listTagByUsedTimes();
        model.addAttribute("tagList",tagList );

        List<Blog> recommendBlogList = blogService.listRecommendBlog();
        model.addAttribute("recommendBlogList",recommendBlogList);

//        System.out.println("---------index-------");
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam String query,Model model){
        List<Blog> mappingBlogs = blogService.searchMappingBlog(query);
        model.addAttribute("mappingBlogs",mappingBlogs);
        return "search";
    }



    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id") Long id,Model model){


        Blog blog = blogService.getBlogAndConvert(id);
        List<Tag> tagList = blogService.listTags(blog.getId());
        model.addAttribute("blog",blog);
        model.addAttribute("comment",new Comment());
        model.addAttribute("tagList",tagList);
        return "blog";
    }

    public String blog3(/*@PathVariable("id")Integer id,@PathVariable("name")String name*/){
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newBlogs(Model model){
        model.addAttribute("newBlogs",blogService.listRecommendBlog());

        return "_fragments :: newBlogList";
    }

}
