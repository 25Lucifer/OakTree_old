package com.tree.blog.web;

import com.tree.blog.service.CategoryService;
import com.tree.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lucifer
 */
@Controller
public class AboutShowController {


    @Autowired
    CategoryService categoryService;

    @Autowired
    TagService tagService;

    @GetMapping("/about")
    public String about(){



        return "about";
    }


}
