package com.cd.blog.dao;

import com.cd.blog.entity.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagDao {

    /* 新增标签 */
    int insertTag(Tag tag);

    /* 删除标签 */
    int deleteTag(Integer tagId);

    /* 修改标签 */
    int updateTag(Tag tag);

    /* 标签列表 */
    List<Tag> queryTagList();

    /* 根据标签名称查询标签 */
    Tag queryTagByName(String TagName);

    /* 根据id查询标签 */
    Tag queryTagById(Integer tagId);


    //----------------------------------------------

    /*获取6个博客数最多的标签，及文章数量*/
    List<Tag> query6Tag();

    /*获取所有含有博客的标签，及其每个标签下的文章数量*/
    List<Tag> queryAllTag();

}
