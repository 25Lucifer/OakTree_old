package com.tree.blog.mapper;

import com.tree.blog.po.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lucifer
 */
@Mapper
@Repository
public interface CommentMapper {


    List<Comment> listRootCommentByBlogId(Long bid);

    List<Comment> listCommentByCommentId(Long cid);

    void saveComment(Comment comment);

}
