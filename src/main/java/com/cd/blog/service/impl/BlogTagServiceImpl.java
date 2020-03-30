package com.cd.blog.service.impl;

import com.cd.blog.dao.BlogTagDao;
import com.cd.blog.entity.BlogTag;
import com.cd.blog.entity.Tag;
import com.cd.blog.service.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("blogTagService")
public class BlogTagServiceImpl implements BlogTagService {

    @Autowired
    private BlogTagDao blogTagDao;

    @Override
    public int batchAddBlogTag(Integer blogId, String tagIds) {
        int effectNum = 0;
        if(blogId != null){
            //先删除该博客原先关联的标签
            blogTagDao.deleteBlogTag(blogId);
            if(tagIds != null && !"".equals(tagIds)){
                List<Integer> tagIdList = stringConvertToList(tagIds);
                List<BlogTag> blogTagList = new ArrayList<>();
                for(Integer tagId : tagIdList){
                    BlogTag blogTag = new BlogTag();
                    blogTag.setBlogId(blogId);
                    blogTag.setTagId(tagId);
                    blogTagList.add(blogTag);
                }
                if(blogTagList.size() >= 1){
                    //再批量插入
                    effectNum = blogTagDao.batchInsertBlogTag(blogTagList);
                    if(effectNum < 1){
                        throw new RuntimeException("博客标签添加失败！");
                    }
                }
            }
        }
        return effectNum;
    }

    //将String类型的数组转化成List集合数组
    private List<Integer> stringConvertToList(String tagIds){
        List<Integer> list = new ArrayList<>();
        if(tagIds != null && !"".equals(tagIds)){
            String[] strSplit = tagIds.split(",");
            for(int i=0; i<strSplit.length; i++){
                list.add(new Integer(strSplit[i]));
            }
        }
        return list;
    }


    @Override
    public List<Tag> getTagsByBlogId(Integer blogId) {
        return blogTagDao.queryTagsByBlogId(blogId);
    }
}
