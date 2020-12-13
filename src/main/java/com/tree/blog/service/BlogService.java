package com.tree.blog.service;

import com.tree.blog.po.Blog;
import com.tree.blog.po.Tag;
import com.tree.blog.vo.BlogQuery;

import java.util.List;
import java.util.Map;

/**
 * @author lucifer
 */
public interface BlogService {

    Blog getBlog(Long id);

    Blog getBlogAndConvert(Long id);

    List<Blog> listBlog();

    List<Blog> listBlog(BlogQuery blogQuery);

    List<Blog> listRecommendBlog(/*int size*/);

    Blog saveBlog(Blog blog, List<Tag> tags);

    List<Tag> listTags(Long bid);

    Long countBlog();

    Map<String,List<Blog>> archiveBlog();

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);

    List<Blog> searchMappingBlog(String query);

    List<Blog> listBlogByCagegory(Long cid);

    List<Blog> listBlogByTag(Long tid);
}
