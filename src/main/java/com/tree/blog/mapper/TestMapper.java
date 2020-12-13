package com.tree.blog.mapper;

import com.tree.blog.po.Test;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lucifer
 */
@Mapper
@Repository
public interface TestMapper {

    List<Test> getTest(Test test);


}
