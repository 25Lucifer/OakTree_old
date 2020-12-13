package com.tree.blog.web.admin;

import com.tree.blog.po.Tag;
import com.tree.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author lucifer
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(Model model){
        /*
        Todo
        加入分页
        int nowPage = Integer.parseInt(page);
        if(page==null){
            nowPage = 1;
        }
        model.addAttribute("page",nowPage);
         */
        List<Tag> tagList = tagService.listTag();
        model.addAttribute("tagList",tagList);
        System.out.println(tagList);
        return "admin/tags";
    }


    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("tag",tagService.getTag(id));

        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String post(@Valid Tag Tag, BindingResult bindingResult, RedirectAttributes attributes){
        Tag TagCheckExit = tagService.getTagByName(Tag.getName());
        if(null != TagCheckExit){
            bindingResult.rejectValue("name","nameError","不能重复添加标签");
        }
        if(bindingResult.hasErrors()){
            return "admin/tags-input";
        }
        Tag Tag2 = tagService.saveTag(Tag);
        if (null == Tag2){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/tags";
    }


    @PostMapping("/tags/{id}")
    public String edit(@Valid Tag tag, BindingResult bindingResult, @PathVariable Long id, RedirectAttributes attributes){
        Tag TagCheckExit = tagService.getTagByName(tag.getName());
        if(null != TagCheckExit){
            bindingResult.rejectValue("name","nameError","不能重复添加标签");
        }
        if(bindingResult.hasErrors()){
            return "admin/tags-input";
        }
        Tag Tag2 = tagService.updateTag(id,tag.getName());
        if (null == Tag2){
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        Tag Tag = tagService.getTag(id);
        if (null == Tag){
            attributes.addFlashAttribute("message","删除成功");
        }else {
            attributes.addFlashAttribute("message","删除失败");
        }
        return "redirect:/admin/tags";
    }


}
