package com.tree.blog.web.admin;

import com.tree.blog.po.Category;
import com.tree.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author lucifer
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/types")
    public String types(Model model){
        /*
        Todo
        加入分页
        int nowPage = Integer.parseInt(page);
        if(page==null){
            nowPage = 1;
        }
        model.addAttribute("page",nowPage);
         */
        List<Category> categoryList = categoryService.listCategory();
        model.addAttribute("categoryList",categoryList);
        System.out.println(categoryList);
        return "admin/types";
    }


    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("category",new Category());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable  Long id,Model model){
        model.addAttribute("category",categoryService.getCategory(id));

        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(@Valid Category category, BindingResult bindingResult, RedirectAttributes attributes){
        Category categoryCheckExit = categoryService.getCategoryByName(category.getName());
        if(null != categoryCheckExit){
            bindingResult.rejectValue("name","nameError","不能重复添加分类");
        }
        if(bindingResult.hasErrors()){
            return "admin/types-input";
        }
        Category category2 = categoryService.saveCategory(category);
        if (null == category2){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }


    @PostMapping("/types/{id}")
    public String edit(@Valid Category category, BindingResult bindingResult, @PathVariable Long id, RedirectAttributes attributes){
        Category categoryCheckExit = categoryService.getCategoryByName(category.getName());
        if(null != categoryCheckExit){
            bindingResult.rejectValue("name","nameError","不能重复添加分类");
        }
        if(bindingResult.hasErrors()){
            return "admin/types-input";
        }
        Category category2 = categoryService.updateCategory(id,category.getName());
        if (null == category2){
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        categoryService.deleteCategory(id);
        Category category = categoryService.getCategory(id);
        if (null == category){
            attributes.addFlashAttribute("message","删除成功");
        }else {
            attributes.addFlashAttribute("message","删除失败");
        }
        return "redirect:/admin/types";
    }


}
