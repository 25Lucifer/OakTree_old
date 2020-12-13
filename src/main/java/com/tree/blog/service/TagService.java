package com.tree.blog.service;

import com.tree.blog.po.Tag;

import java.util.List;

/**
 * @author lucifer
 */
public interface TagService {

    Tag saveTag(Tag Tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    List<Tag> listTagByUsedTimes();

    Tag updateTag(Long id,String name);

    void deleteTag(Long id);

}
