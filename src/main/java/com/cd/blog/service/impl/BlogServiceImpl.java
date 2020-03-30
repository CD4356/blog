package com.cd.blog.service.impl;

import com.cd.blog.dao.BlogDao;
import com.cd.blog.dto.QueryBlog;
import com.cd.blog.entity.Blog;
import com.cd.blog.service.BlogService;
import com.cd.blog.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;


    @Transactional
    @Override
    public List<Blog> getBlogs() {
        return blogDao.queryBlogs();
    }


    @Transactional
    @Override
    public List<Blog> getBlogList(QueryBlog blogCondition) {
        List<Blog> blogList = null;
        try{
            blogList = blogDao.queryBlogList(blogCondition);
        }catch (Exception e){
            throw new RuntimeException("获取博客列表失败："+e.getMessage());
        }
        return blogList;
    }


    @Transactional
    @Override
    public Blog getBlogById(Integer blogId) {
        return blogDao.queryBlogById(blogId);
    }


    @Override
    public int addBlog(Blog blog) {
        int effectNum = 0;
        //如果博客信息不为空，并且博客id为空，则新增博客
        if(blog != null){
            blog.setCreateTime(new Date());
            blog.setLastEditTime(new Date());
            blog.setViews(0);
            try{
                effectNum = blogDao.insertBlog(blog);
                if(effectNum <= 0){
                    throw new RuntimeException("博客保存失败!");
                }
            }catch (Exception e){
                throw new RuntimeException("博客保存失败："+e.getMessage());
            }
        }else {
            throw new RuntimeException("博客信息不能为空!");
        }
        return effectNum;
    }


    @Override
    public int modifyBlog(Blog blog) {
        int effectNum = 0;
        //如果博客信息不为空，且博客id不为空，则编辑博客
        if(blog != null && blog.getBlogId() != null){
            blog.setLastEditTime(new Date());
            blog.setViews(blogDao.queryBlogViews(blog.getBlogId()));
            try{
                effectNum = blogDao.updateBlog(blog);
                if(effectNum < 1){
                    throw new RuntimeException("修改失败咯!");
                }
            }catch (Exception e){
                throw new RuntimeException("博客修改失败："+e.getMessage());
            }
        }else {
            throw new RuntimeException("博客信息不能为空!");
        }
        return effectNum;
    }


    @Transactional
    @Override
    public int removeBlog(Integer blogId) {
        return blogDao.deleteBlog(blogId);
    }


    //-----------------------------------------------------------------------------------------------------------


    @Override
    public List<Blog> getPublishedBlog() {
        return blogDao.queryPublishedBlog();
    }


    @Override
    public List<Blog> get5RecommendBlog() {
        return blogDao.query5RecommendBlog();
    }


    @Override
    public List<Blog> getBlogByTitle(String blogTitle) {
        return blogDao.queryBlogByTitle(blogTitle);
    }


    @Transactional
    @Override
    public Blog getBlogAndConvert(Integer blogId) {
        //根据id获取博客信息
        Blog blog = blogDao.queryBlogById(blogId);
        //获取博客的content内容
        String blogContent = blog.getContent();
        //将Markdown格式的content内容 转化成 HTML格式的内容
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blogContent));
        //浏览博客，博客浏览量相应+1
        int effectedNum = blogDao.updateBlogViews(blogId);
        if(effectedNum < 1){
            throw new RuntimeException("浏览量");
        }
        return blog;
    }

    @Override
    public List<Blog> getBlogsByTypeId(Integer typeId) {
        return blogDao.queryBlogsByTypeId(typeId);
    }

    @Override
    public List<Blog> getBlogsByTagId(Integer tagId) {
        return blogDao.queryBlogsByTagId(tagId);
    }


    //-----------------------------------------------------------------------------------------------------------

    @Override
    public Long getBlogsCount() {
        return blogDao.queryBlogsCount();
    }

    @Override
    public Map<String, List<Blog>> blogArchive() {
        List<String> yearMonths = blogDao.yearMonths();
        Map<String, List<Blog>> map = new LinkedHashMap<>(); //
        for (String yearMonth : yearMonths){
            map.put(yearMonth, blogDao.getBlogsByYearMonth(yearMonth));
        }
        return map;
    }

}
