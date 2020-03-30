package com.cd.blog.web.front;

import com.cd.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: 落叶无痕
 * @Date： 2020/3/23 13:24
 */
@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blog_archive")
    public String blog_archive(Model model){
        model.addAttribute("blogCount",blogService.getBlogsCount());
        model.addAttribute("blogArchive", blogService.blogArchive());
        return "front/blog_archive";
    }

}
