package com.tree.blog;

import com.tree.blog.mapper.TestMapper;
import com.tree.blog.po.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private TestMapper testMapper;


    @Test
    void contextLoads() {


    }

    @Test
    void test1(){

        com.tree.blog.po.Test test = new com.tree.blog.po.Test();
        test.setName("测试名1");
        Category category = new Category();
        category.setName("Go");
        test.setCategory(category);
        List<com.tree.blog.po.Test> res = testMapper.getTest(test);
        System.out.println(res);
    }

}
