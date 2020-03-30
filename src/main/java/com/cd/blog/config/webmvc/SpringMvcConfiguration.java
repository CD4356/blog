package com.cd.blog.config.webmvc;

import com.cd.blog.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class SpringMvcConfiguration implements WebMvcConfigurer {

    //无逻辑页面跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /* 前台展示页面路径映射 */
//        registry.addViewController("/blog_search_list").setViewName("front/blog_search_list");
//        registry.addViewController("/blog_detail").setViewName("front/blog_detail");
//        registry.addViewController("/blog_type").setViewName("front/blog_type");
//        registry.addViewController("/blog_tag").setViewName("front/blog_tag");
//        registry.addViewController("/blog_archive").setViewName("front/blog_archive");
        registry.addViewController("/about_me").setViewName("front/about_me");
        registry.addViewController("/blog_plugin").setViewName("front/blog_plugin");

        //进入登陆页面
        registry.addViewController("/login").setViewName("admin/login");
        //进入新增分类页面
        registry.addViewController("/admin/type_input").setViewName("admin/type_input");
        //进入新增标签页面
        registry.addViewController("/admin/tag_input").setViewName("admin/tag_input");

    }


    //静态资源访问映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 配置图片虚拟路径，访问外部图片：当URL读到/upload/时，就会自动解析成/home/blog/image/upload/
         */
        registry.addResourceHandler("/upload/**").addResourceLocations("file:/home/blog/image/upload/");
    }


    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册LoginInterceptor自定义拦截器
        InterceptorRegistration loginInterceptor = registry.addInterceptor(new LoginInterceptor());
        /*要拦截的请求 (支持*通配符)*/
        loginInterceptor.addPathPatterns("/admin/**");
        /*不拦截的请求 (支持*通配符)*/
        loginInterceptor.excludePathPatterns("/admin/login");
    }



    /**
     * 文件上传解析器
     * @return
     */
//    @Bean("multipartResolver")
//    public CommonsMultipartResolver createMultipartResolver(){
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        multipartResolver.setDefaultEncoding("UTF-8");
//        // 1024*1024*20 = 20M
//        multipartResolver.setMaxUploadSize(1024*1024*20);
//        multipartResolver.setMaxInMemorySize(1024*1024*20);
//        return multipartResolver;
//    }
}
