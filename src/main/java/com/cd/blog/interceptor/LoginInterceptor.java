package com.cd.blog.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //如果session中有用户信息，即为已登录状态，则返回true，既不拦截
        if(request.getSession().getAttribute("user") == null){
            //如果session中没有用户信息，即为未登录状态，则重定向会登陆页面，并返回false，
            response.sendRedirect("/blog/login");
            return false;
        }
        return true;
    }

}
