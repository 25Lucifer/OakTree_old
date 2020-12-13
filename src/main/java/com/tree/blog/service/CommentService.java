package com.tree.blog.service;

import com.tree.blog.po.Comment;

import java.util.List;

/**
 * @author lucifer
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long bid);

    Comment saveComment(Comment comment);



}
