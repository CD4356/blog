package com.cd.blog.service;

import com.cd.blog.entity.Tag;

import java.util.List;

public interface TagService {

    /* 标签列表 */
    List<Tag> getTagList();

    /* 删除标签 */
    int removeTag(Integer tagId);

    /* 修改标签 */
    int modifyTag(Tag tag);

    /* 修改标签 */
    int addTag(Tag tag);

    /* 根据标签名称查询标签 */
    Tag getTagByName(String TagName);

    /* 根据id查询标签 */
    Tag getTagById(Integer tagId);


    //----------------------------------------------

    /*查询六个对应博客数最多的标签*/
    List<Tag> get6Tag();

    /*获取所有含有博客的标签，及其每个标签下的文章数量*/
    List<Tag> getAllTag();

}