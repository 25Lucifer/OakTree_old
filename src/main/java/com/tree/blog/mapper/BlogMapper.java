package com.tree.blog.mapper;

import com.tree.blog.po.Blog;
import com.tree.blog.po.Tag;
import com.tree.blog.vo.BlogQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lucifer
 */
@Repository
@Mapper
@CacheConfig(cacheNames = "blog")
public interface BlogMapper {

    @Cacheable(key = "#p0")
    Blog getBlog(Long id);

    Blog getBlogByTitle(String title);

    List<Blog> getBlogByCondition(BlogQuery blogQuery);

    List<Blog> listBlog();

    List<Blog> listRecommendBlog();

    void saveBlog(Blog blog);

    @CachePut(key = "#p0.id")
    void updateBlog(Blog blog);

    @CacheEvict(key = "#p0")
    void deleteBlog(Long id);

    void saveBlogTags(Long bid,List<Long> tids);

    void saveBlogTag(Long bid,Long tid);

    void deleteBlogTag(Long bid,Long tid);

    List<Tag> listTag(Long bid);

    List<Blog> searchMappingBlog(String query);

    void updateViews(Long bid);

    List<Blog> listBlogByCategoryId(Long cid);

    List<Blog> listBlogByTagId(Long tid);

    List<String> findGroupYear();

    List<Blog> findBlogByYear(String year);

    Long countBlog();
}
