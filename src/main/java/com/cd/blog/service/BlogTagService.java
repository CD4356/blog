package com.cd.blog.service;

import com.cd.blog.entity.Tag;

import java.util.List;

public interface BlogTagService {

    //批量新增BlogTag
    int batchAddBlogTag(Integer blogId, String tagIds);

    List<Tag> getTagsByBlogId(Integer blogId);

}
