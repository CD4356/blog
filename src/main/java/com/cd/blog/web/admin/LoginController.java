package com.cd.blog.web.admin;

import com.cd.blog.entity.User;
import com.cd.blog.service.UserService;
import com.cd.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                         HttpServletRequest request, RedirectAttributes attributes){
        User user = userService.checkUser(username);
        password = MD5Utils.code(password);
        if(user != null && user.getUsername().equals(username) && user.getPassword().equals(password)){
            //校验成功后，将密码设置为null，即不要把密码传到前端页面去，不然会很不安全
            user.setPassword(null);
            //校验成功后，将用户信息存入session中
            request.getSession().setAttribute("user",user);
            return "admin/success";
        }else {
            //将错误信息放入重定向中
            attributes.addFlashAttribute("errorMsg", "用户名 或 密码错误");
            return "redirect:/login";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "redirect:/login";
    }

}
