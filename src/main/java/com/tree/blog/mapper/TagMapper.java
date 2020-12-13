package com.tree.blog.mapper;

import com.tree.blog.po.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lucifer
 */
@Mapper
@Repository
public interface TagMapper {

    void saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    List<Tag> listTag();

    List<Tag> listTagByUsedTimes();

    void updateTag(Long id,String name);

    void deleteTag(Long id);

    List<Tag> findByIds(List<String> ids);
}
