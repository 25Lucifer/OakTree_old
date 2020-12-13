package com.tree.blog.service;

import com.tree.blog.exception.NotFoundException;
import com.tree.blog.mapper.TagMapper;
import com.tree.blog.po.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lucifer
 */
@Service
public class TagServiceImpl implements TagService{

    @Autowired
    TagMapper tagMapper;

    @Override
    public Tag saveTag(Tag Tag) {
//        Tag tag_checkexit = tagMapper.getTagByName(Tag.getName());
        tagMapper.saveTag(Tag);
        Tag result = tagMapper.getTagByName(Tag.getName());
        return result;
    }

    @Override
    public Tag getTag(Long id) {
        Tag Tag = tagMapper.getTag(id);
        return Tag;
    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Override
    public void deleteTag(Long id) {
        tagMapper.deleteTag(id);
    }

    @Override
    public List<Tag> listTag() {
        List<Tag> tags = tagMapper.listTag();
        return tags;
    }

    @Override
    public List<Tag> listTag(String ids) {
        List<String> tids = new ArrayList<>();
        for(String s : ids.split(",")){
            tids.add(s);
        }
        List<Tag> tags = tagMapper.findByIds(tids);
        return tags;
    }

    @Override
    public List<Tag> listTagByUsedTimes() {
        return tagMapper.listTagByUsedTimes();
    }

    @Override
    public Tag updateTag(Long id, String name) {
        Tag Tag = tagMapper.getTag(id);
        if(null == Tag){
            throw new NotFoundException("不存在该类型");
        }
        tagMapper.updateTag(id,name);
        Tag = tagMapper.getTagByName(name);
        return Tag;
    }
}
