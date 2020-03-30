package com.cd.blog.web.admin;


import com.cd.blog.entity.Tag;
import com.cd.blog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    //标签列表
    @GetMapping("/tags")
    public String tag_list(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,10);
        //获取标签列表
        List<Tag> tagList = tagService.getTagList();
        //得到分页结果对象,分页信息都保存在pageInfo中
        PageInfo<Tag> pageInfo = new PageInfo<>(tagList);
        //打印pageInfo分页信息
        System.out.println(pageInfo.toString());
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    //新增标签
    @PostMapping("/tag/add")
    public String add_tag(Tag tag, RedirectAttributes attributes){
        //根据标签名获取标签信息，用于判断该标签是否已经存在了
        Tag checkTagName = tagService.getTagByName(tag.getTagName());
        if(checkTagName != null){
            attributes.addFlashAttribute("message","该标签已存在，无需重复添加！");
            return "redirect:/admin/tag_input";
        }
        //如果该标签不存在，则添加标签
        int num = tagService.addTag(tag);
        if (num >= 1){
            attributes.addFlashAttribute("message","恭喜您，添加成功咯！");
            return "redirect:/admin/tags";
        }else {
            attributes.addFlashAttribute("message","对不起，添加失败啦！");
            return "redirect:/admin/tag_input";
        }
    }

    //删除标签
    @GetMapping("/tag/{id}/delete")
    public String delete_tag(@PathVariable("id") Integer id, RedirectAttributes attributes){
        int num = tagService.removeTag(id);
        if (num >= 1){
            attributes.addFlashAttribute("message","恭喜您，删除成功咯！");
        }else {
            attributes.addFlashAttribute("message","对不起，删除失败啦！");
        }
        return "redirect:/admin/tags";
    }


/* ------------------------------------------------------------------- ---------------------------------------------- */


    //进入编辑标签页面
    @GetMapping("/to_edit_tag{id}")
    public String to_edit_tag(Integer id, Model model){
        Tag tag = tagService.getTagById(id);
        model.addAttribute("tag",tag);
        return "admin/tag_edit";
    }

    //编辑标签
    @PostMapping("/tag/edit")
    public String edit_tag(Tag tag, RedirectAttributes attributes){
        //根据标签名获取标签信息，用于判断要修改的标签名称是否已经存在了
        Tag checkTagName = tagService.getTagByName(tag.getTagName());
        if(checkTagName != null){
            attributes.addFlashAttribute("message","该标签已存在，无需重复添加！");
            return "redirect:/admin/tag_input";
        }
        //如果该标签名称尚不存在，则允许修改
        int num = tagService.modifyTag(tag);
        if (num >= 1){
            attributes.addFlashAttribute("message","恭喜您，修改成功咯！");
        }else {
            attributes.addFlashAttribute("message","对不起，修改失败啦！");
        }
        return "redirect:/admin/tags";
    }

}
