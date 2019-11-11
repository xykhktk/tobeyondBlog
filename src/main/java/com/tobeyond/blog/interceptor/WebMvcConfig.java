package com.tobeyond.blog.interceptor;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 向mvc中添加自定义组件
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private BaseInterceptor baseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ///admin/**,一个*不行，要2个 -_-!
        registry.addInterceptor(baseInterceptor).addPathPatterns("/admin/**").addPathPatterns("/admin");//pc后台的拦截器
//        registry.addInterceptor(baseInterceptor).addPathPatterns("/api/admin/**").addPathPatterns("/admin"); //api后台的拦截器
    }

}
