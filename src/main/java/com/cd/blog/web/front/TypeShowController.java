package com.cd.blog.web.front;

import com.cd.blog.dao.TypeDao;
import com.cd.blog.entity.Blog;
import com.cd.blog.entity.Type;
import com.cd.blog.service.BlogService;
import com.cd.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: 落叶无痕
 * @Date： 2020/3/21 23:20
 */
@Controller
public class TypeShowController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;


    @GetMapping("/blog_type/{id}")
    public String types(Model model, @PathVariable("id") Integer id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        List<Type> types = typeService.getAllType();
        if(id == -1){
            id = types.get(0).getTypeId();
        }
        model.addAttribute("types",types);
        model.addAttribute("activeTypeId",id);
        PageHelper.startPage(pageNum,5);
        List<Blog> blogList = blogService.getBlogsByTypeId(id);
        //得到分页结果对象,分页信息都保存在pageInfo中
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        //打印pageInfo分页信息
        System.out.println(pageInfo.toString());
        model.addAttribute("pageInfo",pageInfo);
        return "front/blog_type";
    }

}
