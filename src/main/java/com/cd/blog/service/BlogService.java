package com.cd.blog.service;

import com.cd.blog.dto.QueryBlog;
import com.cd.blog.entity.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {


    /* 获取全部博客 */
    List<Blog> getBlogs();

    /* 根据组合条件获取博客列表 */
    List<Blog> getBlogList(QueryBlog blogCondition);

    /* 根据博客id查询博客，用于后台获取博客信息填充到修改博客页面 */
    Blog getBlogById(Integer blogId);

    /* 新增博客 */
    int addBlog(Blog blog);

    /* 修改博客 */
    int modifyBlog(Blog blog);

    /* 删除博客 */
    int removeBlog(Integer blogId);


    //----------------------------------------------

    /* 前台首页博客列表 */
    List<Blog> getPublishedBlog();

    /* 获取5篇推荐博客用于首页展示 */
    List<Blog> get5RecommendBlog();

    /* 前台根据全局搜索输入的文章标题获取博客列表，再通过pageHelper进行分页 */
    List<Blog> getBlogByTitle(String blogTitle);

    /* 根据博客id查询博客，将博客的content内容的Markdown语法转化成HTML，渲染到博客详情页 */
    Blog getBlogAndConvert(Integer blogId);

    /* 根据分类获取博客列表，再通过pageHelper进行分页 */
    List<Blog> getBlogsByTypeId(Integer typeId);

    /* 根据标签id获取博客列表，再通过pageHelper进行分页 */
    List<Blog> getBlogsByTagId(Integer tagId);

    //----------------------------------------------

    //获取所有已发布博客的数目
    Long getBlogsCount();

    /* 博客归档 */
    Map<String, List<Blog>> blogArchive();

}
