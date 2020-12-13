package com.tree.blog.vo;

import com.tree.blog.po.Comment;

import java.util.List;

/**
 * @author lucifer
 */
public class CommentView {
    Comment comment;
    List<Comment> comments;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
