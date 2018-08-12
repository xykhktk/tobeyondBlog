package com.tobeyond.blog;

import com.tobeyond.blog.interceptor.BaseInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@MapperScan("com.tobeyond.blog.dao.mapper")	//No MyBatis mapper was found in '[com.tobeyond.blog]' package. Please check your configuration.
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}


	@Bean
	public FilterRegistrationBean csrfFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new CsrfFilter(new HttpSessionCsrfTokenRepository()));
		registration.addUrlPatterns("/*");
		return registration;
	}

//	@Configuration
//	public class WebMvcConfg extends WebMvcConfigurationSupport {
//		//增加拦截器
//		public void addInterceptors(InterceptorRegistry registry){
//			registry.addInterceptor(new BaseInterceptor()) //指定拦截器类
//			;
//		}
//	}

}
