package com.cd.blog.dao;

import com.cd.blog.entity.BlogTag;
import com.cd.blog.entity.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogTagDao {

    //批量新增BlogTag
    int batchInsertBlogTag(List<BlogTag> blogTagList);

    //根据博客id删除
    int deleteBlogTag(Integer blogId);

    //根据博客id获取关联的标签
    List<Tag> queryTagsByBlogId(Integer blogId);

}
