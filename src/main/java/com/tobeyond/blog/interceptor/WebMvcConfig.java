package com.tobeyond.blog.interceptor;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 向mvc中添加自定义组件
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private AdminPcInterceptor adminPcInterceptor;

    @Resource
    private AdminApiInterceptor adminApiInterceptor; //@Resource和autoware区别

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ///admin/**,一个*不行，要2个 -_-!
        registry.addInterceptor(adminPcInterceptor).addPathPatterns("/admin/**").addPathPatterns("/admin");//pc后台的拦截器
        registry.addInterceptor(adminApiInterceptor).addPathPatterns("/api/admin/**").addPathPatterns("/admin"); //api后台的拦截器
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/admin/**").allowedOrigins("http://127.0.0.1:8000");
        registry.addMapping("/api/admin/**").allowedOrigins("http://localhost:8000");
    }
}
