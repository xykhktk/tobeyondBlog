package com.tobeyond.blog.interceptor;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * 向mvc中添加自定义组件
 * Created by BlueT on 2017/3/9.
 */
@Component
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Resource
    private BaseInterceptor baseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ///admin/**,一个*不行，要2个 -_-!
        registry.addInterceptor(baseInterceptor).addPathPatterns("/admin/**").addPathPatterns("/admin");
    }

    /**
     * 添加静态资源文件，外部可以直接访问地址
     * @param registry
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/upload/**").addResourceLocations("file:"+ CommonUtils.getUploadFilePath()+"upload/");
//        super.addResourceHandlers(registry);
//    }
}
