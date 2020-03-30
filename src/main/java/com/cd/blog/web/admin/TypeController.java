package com.cd.blog.web.admin;

import com.cd.blog.entity.Type;
import com.cd.blog.service.TypeService;
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
public class TypeController {

    @Autowired
    private TypeService typeService;

    //分类列表
    @GetMapping("/types")
    public String type_list(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,10);
        List<Type> typeList = typeService.getTypeList();
        //得到分页结果对象,分页信息都保存在pageInfo中
        PageInfo<Type> pageInfo = new PageInfo<>(typeList);
        //打印pageInfo分页信息
        System.out.println(pageInfo.toString());
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    //新增分类
    @PostMapping("/type/add")
    public String add_type(Type type, RedirectAttributes attributes){
        System.out.println("前端传过来的表单: " + type);
        Type checkTypeName = typeService.getTypeByName(type.getTypeName());
        if(checkTypeName != null){
            attributes.addFlashAttribute("message","该分类已存在，无需重复添加！");
            return "redirect:/admin/type_input";
        }
        int num = typeService.addType(type);
        if (num >= 1){
            attributes.addFlashAttribute("message","恭喜您，添加成功咯！");
        }else {
            attributes.addFlashAttribute("message","对不起，添加失败啦！");
        }
        return "redirect:/admin/type_input";
    }

    //删除分类
    @GetMapping("/type/delete{id}")
    public String delete_type(Integer id, RedirectAttributes attributes){
        int num = typeService.removeType(id);
        if (num >= 1){
            attributes.addFlashAttribute("message","恭喜您，删除成功咯！");
        }else {
            attributes.addFlashAttribute("message","对不起，删除失败啦！");
        }
        return "redirect:/admin/types";
    }


/* ------------------------------------------------------------------- ---------------------------------------------- */


    //进入编辑标签页面
    @GetMapping("/to_edit_type{id}")
    public String to_edit_type(Integer id, Model model){
        Type type = typeService.getTypeById(id);
        model.addAttribute("type",type);
        return "admin/type_edit";
    }

    //编辑分类
    @PostMapping("/type/edit")
    public String edit_type(Type type, RedirectAttributes attributes){
        //根据分类名获取分类信息，用于判断要修改的分类名称是否已经存在了
        Type checkTypeName = typeService.getTypeByName(type.getTypeName());
        if(checkTypeName != null){
            attributes.addFlashAttribute("message","该标签已存在咯！");
            return "redirect:/admin/type_input";
        }
        //如果该分类名称尚不存在，则允许修改
        int num = typeService.modifyType(type);
        if (num >= 1){
            attributes.addFlashAttribute("message","恭喜您，修改成功咯！");
        }else {
            attributes.addFlashAttribute("message","对不起，修改失败啦！");
        }
        return "redirect:/admin/type_input";
    }


}
