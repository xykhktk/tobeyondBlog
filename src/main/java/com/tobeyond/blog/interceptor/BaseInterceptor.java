package com.tobeyond.blog.interceptor;

import com.tobeyond.blog.model.Bo.UserCustom;
import com.tobeyond.blog.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 * Created by BlueT on 2017/3/9.
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {
    private static final Logger LOGGE = LoggerFactory.getLogger(BaseInterceptor.class);
    private static final String USER_AGENT = "user-agent";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String contextPath = request.getContextPath();
        // System.out.println(contextPath);
        String uri = request.getRequestURI();

        //请求拦截处理
        UserCustom user = CommonUtils.getLoginUser(request);
        System.out.print(contextPath + "/admin/login");
        System.out.print("---");
        System.out.print(uri);
        System.out.print("---");
        System.out.print(user);
        if ( null == user &&
                !uri.startsWith(contextPath + "/admin/login") &&
//                !uri.startsWith(contextPath + "/admin/js") &&
//                !uri.startsWith(contextPath + "/admin/css") &&
//                !uri.startsWith(contextPath + "/admin/fonts") &&
//                !uri.startsWith(contextPath + "/admin/images") &&
//                !uri.startsWith(contextPath + "/admin/lib") &&
                !uri.startsWith(contextPath + "/admin/logout")
                ) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return false;
        }

//        if (null == user) {
//            Integer uid = TaleUtils.getCookieUid(request);
//            if (null != uid) {
//                //这里还是有安全隐患,cookie是可以伪造的
//                user = userService.queryUserById(uid);
//                request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, user);
//            }
//        }
//        if (uri.startsWith(contextPath + "/admin") && !uri.startsWith(contextPath + "/admin/login") && null == user) {
//            response.sendRedirect(request.getContextPath() + "/admin/login");
//            return false;
//        }
        //设置get请求的token
//        if (request.getMethod().equals("GET")) {
//            String csrf_token = UUID.UU64();
//            // 默认存储30分钟
//            cache.hset(Types.CSRF_TOKEN.getType(), csrf_token, uri, 30 * 60);
//            request.setAttribute("_csrf_token", csrf_token);
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        OptionVo ov = optionService.getOptionByName("site_record");
//        httpServletRequest.setAttribute("commons", commons);//一些工具类和公共方法
//        httpServletRequest.setAttribute("option", ov);
//        httpServletRequest.setAttribute("adminCommons", adminCommons);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
