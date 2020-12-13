package com.tree.blog.web.admin;

import com.tree.blog.po.Blog;
import com.tree.blog.po.Category;
import com.tree.blog.po.Tag;
import com.tree.blog.po.User;
import com.tree.blog.service.BlogService;
import com.tree.blog.service.CategoryService;
import com.tree.blog.service.TagService;
import com.tree.blog.service.UserService;
import com.tree.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author lucifer
 */

@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;


    @GetMapping("/blogs")
    public String blogs(BlogQuery blogQuery, Model model){
        List<Blog> blogs = blogService.listBlog(blogQuery);
        model.addAttribute("blogs",blogs);
        model.addAttribute("categories",categoryService.listCategory());
        return LIST;
    }

    @PostMapping("/blogs/search")
    public String blogList(BlogQuery blogQuery, Model model){
        List<Blog> blogs = blogService.listBlog(blogQuery);
        model.addAttribute("blogs",blogs);
        return "admin/blogs :: blogList";
    }

    private void setCategoryAndTag(Model model){
        model.addAttribute("categories",categoryService.listCategory());
        model.addAttribute("tags",tagService.listTag());
    }

    @GetMapping("/blogs/input")
    public String input(Model model){
        setCategoryAndTag(model);
        Blog blog = new Blog();
//        Category category = new Category();
//        blog.setCategory(category);
        model.addAttribute("blog",blog);
        return INPUT;
    }

    private String tagsToIds(List<Tag> tags){
        if(!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for(Tag tag : tags){
                if(flag){
                    ids.append(",");
                }else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else {
            return null;
        }
    }


    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model){
        setCategoryAndTag(model);
        Blog blog = blogService.getBlog(id);
        List<Tag> tags = blogService.listTags(id);
        blog.setTagIds(tagsToIds(tags));
        model.addAttribute("blog",blog);
        return INPUT;
    }

    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes redirectAttributes, HttpSession session){
        User user = (User)session.getAttribute("user");
        user = userService.getUserByUsername(user.getUsername());
        blog.setUser(user);
        blog.setCategory(categoryService.getCategory(blog.getCategory().getId()));
        System.out.println(blog.getTagIds());
        List<Tag> tags = tagService.listTag(blog.getTagIds());
        Blog b = blogService.saveBlog(blog,tags);
        if(null == b){
            redirectAttributes.addFlashAttribute("message","新增失败");
        }else {
            redirectAttributes.addFlashAttribute("message","新增成功");
        }

        return REDIRECT_LIST;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes redirectAttributes){
        blogService.deleteBlog(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return REDIRECT_LIST;
    }


}
