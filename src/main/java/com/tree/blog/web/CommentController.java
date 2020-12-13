package com.tree.blog.web;

import com.tree.blog.po.Comment;
import com.tree.blog.po.User;
import com.tree.blog.service.BlogService;
import com.tree.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author lucifer
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable("blogId") Long bid, Model model){
        List<Comment> comments = commentService.listCommentByBlogId(bid);
        model.addAttribute("comments",comments);

        return "blog :: commentList";
    }



    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        System.out.println(comment);
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        if(null!=user){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
            comment.setNickname(user.getNickname());
        }
        commentService.saveComment(comment);
        return "redirect:/comments/"+comment.getBid();
    }

}
