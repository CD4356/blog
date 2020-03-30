package com.cd.blog.dao;

import com.cd.blog.dto.QueryBlog;
import com.cd.blog.entity.Blog;
import com.cd.blog.entity.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BlogDao {

    /* 获取全部博客，再通过pageHelper进行分页 */
    List<Blog> queryBlogs();

    /* 后台根据组合条件获取博客列表，再通过pageHelper进行分页 */
    List<Blog> queryBlogList(@Param("blogCondition") QueryBlog blogCondition);

    /*根据博客id查询博客，用于后台获取博客信息填充到修改博客页面*/
    Blog queryBlogById(Integer blogId);

    /* 新增博客 */
    int insertBlog(Blog blog);

    /* 修改博客 */
    int updateBlog(Blog blog);

    /* 删除博客 */
    int deleteBlog(Integer blogId);


    //----------------------------------------------

    /* 前台首页博客列表 */
    List<Blog> queryPublishedBlog();

    /* 获取5篇推荐博客用于首页展示 */
    List<Blog> query5RecommendBlog();

    /* 前台根据全局搜索输入的文章标题获取博客列表，再通过pageHelper进行分页 */
    List<Blog> queryBlogByTitle(String blogTitle);

    /* 根据分类id获取博客列表，再通过pageHelper进行分页 */
    List<Blog> queryBlogsByTypeId(Integer typeId);

    /* 根据标签id获取博客列表，再通过pageHelper进行分页 */
    List<Blog> queryBlogsByTagId(Integer tagId);

    /*根据博客id修改博客的浏览量，每浏览一次该博客，访问量就会+1*/
    int updateBlogViews(Integer blogId);

    /*获取已发布博客的浏览次数，用于修改博客内容时使用*/
    int queryBlogViews(Integer blogId);


    //----------------------------------------------

    //分组获取博客的创建日期（精确到月）
    List<String> yearMonths();

    //根据创建日期（精确到月），获取博客列表
    List<Blog> getBlogsByYearMonth(String yearMonth);

    //获取所有已发布博客的数目
    Long queryBlogsCount();
}
