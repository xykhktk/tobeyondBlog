package com.tobeyond.blog.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.tobeyond.blog.annotation.AdminLoginToken;
import com.tobeyond.blog.annotation.PassToken;
import com.tobeyond.blog.model.bo.UserCustom;
import com.tobeyond.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class AdminApiInterceptor implements HandlerInterceptor {

    @Autowired
    IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        //method.getClass().isAnnotationPresent(AdminLoginToken.class)  //获取类的注解,用getClass获取不到。须用getDeclaringClass
        AdminLoginToken userLoginToken = method.getAnnotation(AdminLoginToken.class);
        if(userLoginToken == null){
            userLoginToken = method.getDeclaringClass().getAnnotation(AdminLoginToken.class);
        }

        if (userLoginToken != null && userLoginToken.required()) {
            // 执行认证
            if (token == null) {
                throw new RuntimeException("无token，请重新登录");
            }
            // 获取 token 中的 user id
            String userId;
            try {
                userId = JWT.decode(token).getAudience().get(0);
            } catch (JWTDecodeException j) {
                throw new RuntimeException("401");
            }
            UserCustom user = userService.findUserById(Integer.valueOf(userId));
            if (user == null) {
                throw new RuntimeException("用户不存在，请重新登录");
            }
            // 验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(String.valueOf(user.getId()) + String.valueOf(user.getTokenTime()))).build();
            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                throw new RuntimeException("40001" + e.getMessage());
            }
            return true;
        }
        return true;
    }

}
