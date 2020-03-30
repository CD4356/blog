package com.cd.blog.web.admin;

import com.cd.blog.dto.QueryBlog;
import com.cd.blog.entity.Blog;
import com.cd.blog.entity.Tag;
import com.cd.blog.entity.Type;
import com.cd.blog.entity.User;
import com.cd.blog.service.BlogService;
import com.cd.blog.service.BlogTagService;
import com.cd.blog.service.TagService;
import com.cd.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogTagService blogTagService;


    @GetMapping("/blogs")
    public String blogs(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //分类规则
        PageHelper.startPage(pageNum, 10);
        //博客列表
        List<Blog> blogList = blogService.getBlogs();
        //通过分页信息对象，保存分页信息
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        //打印pageInfo分页信息，方便我们查看分页信息
        System.out.println(pageInfo.toString());
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("typeList",typeService.getTypeList());
        return "admin/blog_list";
    }


    @PostMapping("/blog_search")
    public String blog_search(Model model, QueryBlog blogCondition, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //分类规则
        PageHelper.startPage(pageNum, 10);
        //博客列表
        List<Blog> blogList = blogService.getBlogList(blogCondition);
        //通过分页信息对象，保存分页信息
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        //打印pageInfo分页信息，方便我们查看分页信息
        System.out.println(pageInfo.toString());
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("typeList",typeService.getTypeList());
        return "admin/blog_list :: search_blog_list";  //这里的意思是：返回admin目录下的blog_list页面中的search_blog_list片段，以实现局部刷新
    }


    //进入写博客页面
    @GetMapping("/blog_publish")
    public String blog_publish(Model model){
        model.addAttribute("typeList",typeService.getTypeList());
        model.addAttribute("tagList",tagService.getTagList());
        model.addAttribute("blog", new Blog()); //添加这一句防止页面报错，因为博客新增和编辑共有了一个blog_publish页面，而blog_publish页面有th:value="${blog.xx}",所以需要放入一个blog对象
        return "admin/blog_publish";
    }

    //进入编辑博客页面
    @GetMapping("/to_edit_blog{id}")
    public String to_edit_blog(Integer id, Model model){
        model.addAttribute("typeList",typeService.getTypeList());
        model.addAttribute("tagList",tagService.getTagList());
        //根据传入的blogId获取博客信息，以便渲染修改
        Blog blog = blogService.getBlogById(id);
        //根据传入的blogId获取关联的所以标签，并转化成字符串保存进blog实体对象的tagIds属性中
        blog.setTagIds(blog.tagsToIds(blogTagService.getTagsByBlogId(id)));
        model.addAttribute("blog",blog);

        return "admin/blog_publish";
    }


    //保存博客 或 发布博客 或 编辑博客
    @PostMapping("/blog_save")
    public String blog_save(Blog blog, RedirectAttributes attributes, HttpServletRequest request){
        //设置blog的作者user（从session中获取用户信息来设置）
        blog.setUser((User) request.getSession().getAttribute("user"));
        //设置blog的分类type
        blog.setType(typeService.getTypeById(blog.getType().getTypeId()));

        int num = 0;
        if(blog.getBlogId() == null){
            //博客id为空，则新增博客
            num = blogService.addBlog(blog);
        }else {
            //博客id不为空，则编辑博客
            num = blogService.modifyBlog(blog);
        }
        if (num >= 1){
            attributes.addFlashAttribute("message","恭喜您，操作成功咯！");
        }else {
            attributes.addFlashAttribute("message","对不起，操作失败啦！");
        }

        int effectNum = blogTagService.batchAddBlogTag(blog.getBlogId(),blog.getTagIds());
        return "redirect:/admin/blogs";
    }


    //删除标签
    @GetMapping("/blog/{id}/delete")
    public String delete_blog(@PathVariable("id") Integer id, RedirectAttributes attributes){
        int effectNum = blogService.removeBlog(id);
        if (effectNum >= 1){
            attributes.addFlashAttribute("message","恭喜您，操作成功咯！");
        }else {
            attributes.addFlashAttribute("message","对不起，操作失败啦！");
        }
        return "redirect:/admin/blogs";
    }

}
