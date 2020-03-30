package com.cd.blog.entity;

import java.util.List;

public class Tag {

    /*自增id*/
    private Integer tagId;
    /*分类名称*/
    private String TagName;

    /*博文: 和标签之间是多对多关系*/
    private List<Blog> blogList;

    private Integer num;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return TagName;
    }

    public void setTagName(String tagName) {
        TagName = tagName;
    }

    public List<Blog> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
