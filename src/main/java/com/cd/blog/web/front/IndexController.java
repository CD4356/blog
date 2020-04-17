package com.cd.blog.web.front;

import com.cd.blog.dto.QueryBlog;
import com.cd.blog.entity.Blog;
import com.cd.blog.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 落叶无痕
 * @Date： 2020/3/21 23:20
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CommentService commentService;


    /* 首页博客列表 */
    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){
        //分类规则
        PageHelper.startPage(pageNum, 6);
        //博客列表
        List<Blog> blogList = blogService.getPublishedBlog();
        //通过分页信息对象，保存分页信息
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        //打印pageInfo分页信息，方便我们查看分页信息
        System.out.println(pageInfo.toString());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types", typeService.get6Type());
        model.addAttribute("tags", tagService.get6Tag());
        model.addAttribute("recommend5Blog", blogService.get5RecommendBlog());
        model.addAttribute("commentCount", commentService.getCommentCount());
        model.addAttribute("allViews", blogService.getAllViews());
        return "front/index";
    }


    /* 博客详情 */
    @GetMapping("/blog/{id}")
    public String blog(Model model, @PathVariable("id") Integer id){
        model.addAttribute("blog", blogService.getBlogAndConvert(id));
        model.addAttribute("preBlog", blogService.preBlog(id));
        model.addAttribute("nextBlog", blogService.nextBlog(id));
        return "front/blog_detail";
    }


    /* 全局搜索 */
    @PostMapping("/front/blog_search_list")
    public String blog_search_list(Model model, String blogTitle, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //分类规则
        PageHelper.startPage(pageNum, 1000);
        //博客列表
        List<Blog> blogList = blogService.getBlogByTitle(blogTitle);
        //通过分页信息对象，保存分页信息
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("blogTitle",blogTitle);
        return "front/blog_search_list";
    }

}
