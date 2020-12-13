package com.tree.blog.po;

import javax.validation.constraints.NotBlank;

/**
 * @author lucifer
 */
public class Tag {
    private Long id;

    @NotBlank(message = "标签名称不可为空")
    private String name;

    private int blogCount;

    public Tag() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(int blogCount) {
        this.blogCount = blogCount;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blogCount=" + blogCount +
                '}';
    }
}
