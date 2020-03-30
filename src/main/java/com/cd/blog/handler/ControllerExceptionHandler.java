package com.cd.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    //日志记录器
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Model model, HttpServletRequest request, Exception e) throws Exception {
        logger.error("error信息：",e);
        //如果是带有状态码注解的url，则不去拦截
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        model.addAttribute("url",request.getRequestURL());
        model.addAttribute("exception",e);
        return "/error/error";
    }

}
