package com.tree.blog.service;

import com.tree.blog.exception.NotFoundException;
import com.tree.blog.mapper.BlogMapper;
import com.tree.blog.mapper.TagMapper;
import com.tree.blog.po.Blog;
import com.tree.blog.po.Tag;
import com.tree.blog.util.MarkdownUtils;
import com.tree.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author lucifer
 */
@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    BlogMapper blogMapper;


    @Override
    public Blog getBlog(Long id) {
        return blogMapper.getBlog(id);
    }

    @Override
    public Blog getBlogAndConvert(Long id) {
        Blog blog = blogMapper.getBlog(id);
        if(null == blog){
            throw new NotFoundException("博客不存在");
        }
        String context = blog.getContext();
        String html = MarkdownUtils.markdownToHtmlExtensions(context);
        blog.setContext(html);

        blogMapper.updateViews(id);
        return blog;
    }

    @Override
    public List<Blog> listBlog() {
        return blogMapper.listBlog();
    }

    @Override
    public List<Blog> listBlog(BlogQuery blogQuery) {
        return blogMapper.getBlogByCondition(blogQuery);
    }

    @Override
    public List<Blog> listRecommendBlog(/*int size*/) {
//        List<Blog> blogs = blogMapper.listRecommendBlog();
//        List<Blog> need = new ArrayList<>();
//        for(int i=0;i<size;i++){
//            need.add(blogs.get(i));
//        }
//        return need;
        return blogMapper.listRecommendBlog();
    }

    @Override
    public Blog saveBlog(Blog blog,List<Tag> tags) {
        if(null == blog.getId()){
            Blog blog1;
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
            blogMapper.saveBlog(blog);
            blog1 = blogMapper.getBlogByTitle(blog.getTitle());

            List<Long> tids = new ArrayList<>();
            for(Tag tag:tags){
                tids.add(tag.getId());
            }
            if(tids.size()!=0){
                blogMapper.saveBlogTags(blog1.getId(),tids);
            }
            return blog1;
        }else {
            blog.setUpdateTime(new Date());
            blogMapper.updateBlog(blog);
            //先获取原先的tags
            List<Tag> formerTags = listTags(blog.getId());
            //与新的tags做比较,多的增加少的删除
                //去掉相同的元素，formerTags剩下的是删除的 tags剩下的是新增的
//            for(int i=0;i<formerTags.size();i++){
//                for(int j=0;j<tags.size()&&j!=-1;j++){
//                    if(formerTags.get(i).getId().equals(tags.get(j).getId())&&formerTags.get(i).getName().equals(tags.get(j).getName())){
//                        formerTags.remove(i);
//                        tags.remove(j);
//                        j=-1;
//                    }
//                }
//            }


            //I found that delete all then add all is most direct way.
            for(Tag tag : formerTags){
                //删除blog_tag中对应的数据
                blogMapper.deleteBlogTag(blog.getId(),tag.getId());
            }
            for(Tag tag : tags){
                //增加blog_tag中对应的数据
                blogMapper.saveBlogTag(blog.getId(),tag.getId());
            }
            return blog;
        }

    }

    @Override
    public List<Tag> listTags(Long bid) {
        return blogMapper.listTag(bid);
    }

    @Override
    public Long countBlog() {
        return blogMapper.countBlog();
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogMapper.findGroupYear();
        Map<String,List<Blog>> map = new HashMap<>();
        for(String year : years){
            List<Blog> blogs = blogMapper.findBlogByYear(year);
            map.put(year,blogs);
        }


        return map;
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        if(null == blogMapper.getBlog(id)){
            throw new NotFoundException("该文章不存在");
        }
        blog.setId(id);
        blogMapper.updateBlog(blog);
        return blogMapper.getBlogByTitle(blog.getTitle());
    }

    @Override
    public void deleteBlog(Long id) {
        if(null == blogMapper.getBlog(id)){
            throw new NotFoundException("该文章不存在");
        }
        blogMapper.deleteBlog(id);
    }

    @Override
    public List<Blog> searchMappingBlog(String query) {
        return blogMapper.searchMappingBlog(query);
    }

    @Override
    public List<Blog> listBlogByCagegory(Long cid) {
        return blogMapper.listBlogByCategoryId(cid);
    }

    @Override
    public List<Blog> listBlogByTag(Long tid) {
        return blogMapper.listBlogByTagId(tid);
    }
}
