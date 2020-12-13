package com.tree.blog.service;

import com.tree.blog.mapper.CommentMapper;
import com.tree.blog.po.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lucifer
 */
@Service
public class CommentServiceImpl implements CommentService{


    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Comment> listCommentByBlogId(Long bid) {
        List<Comment> comments = commentMapper.listRootCommentByBlogId(bid);
        for(Comment comment : comments){
            comment.setReplys(commentMapper.listCommentByCommentId(comment.getId()));
        }
        return comments;
    }

    @Override
    public Comment saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        comment.setAvatar("https://picsum.photos/id/1011/1000/1000");
        commentMapper.saveComment(comment);
        return comment;
    }

    private List<Comment> eachComment(List<Comment> comments){
        List<Comment> commentView = new ArrayList<>();
        for(Comment comment : comments){
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentView.add(c);
        }
        combineChildren(commentView);
        return commentView;
    }

    private void combineChildren(List<Comment> comments){
//        for(Comment comment : comments){
//            List<Comment> replys1 = comment.
//        }
    }

}
