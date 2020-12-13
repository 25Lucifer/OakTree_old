package com.tree.blog.web;

import com.tree.blog.po.Category;
import com.tree.blog.po.Tag;
import com.tree.blog.service.BlogService;
import com.tree.blog.service.CategoryService;
import com.tree.blog.service.TagService;
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
public class TagShowController {


    @Autowired
    TagService tagService;

    @Autowired
    BlogService blogService;


    @GetMapping("/tags/{id}")
    public String tags(@PathVariable("id") Long id, Model model){

        List<Tag> tagList = tagService.listTagByUsedTimes();
        model.addAttribute("tagList",tagList);

        System.out.println(tagList);

        if(id == -1){
            id = tagList.get(0).getId();
        }
        model.addAttribute("blogList",blogService.listBlogByTag(id));
        model.addAttribute("activeTagId",id);
        return "tags";
    }


}
