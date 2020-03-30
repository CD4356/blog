package com.cd.blog.entity;

import java.util.List;

public class Type {

    /*自增id*/
    private Integer typeId;
    /*标签名称*/
    private String typeName;

    /*博文: 和分类之间是一对多关系，一个分类可以含有多篇博文*/
    private List<Blog> blogList;

    private Integer num;


    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
