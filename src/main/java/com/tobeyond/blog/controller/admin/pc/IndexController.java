package com.tobeyond.blog.controller.admin.pc;

import com.tobeyond.blog.constant.WebConst;
import com.tobeyond.blog.model.Bo.UserCustom;
import com.tobeyond.blog.model.Dto.ReturnJson;
import com.tobeyond.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller("adminIndexController")
@RequestMapping(value = "/admin")
public class IndexController {

    private ModelAndView modelAndView;

    @Autowired
    IUserService userService;

    @GetMapping(value = {"","/","/index"})
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("/admin/index");
        return  modelAndView;
    }

    @GetMapping(value = "/welcome")
    public ModelAndView welcome(){
        ModelAndView modelAndView = new ModelAndView("/admin/welcome");
        return  modelAndView;
    }

    @GetMapping(value = "/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("/admin/login");
        return  modelAndView;
    }

    @ResponseBody
    @PostMapping(value = "/login")
    public ReturnJson login(@RequestParam String name, @RequestParam String password,
                            HttpServletRequest request){

        ReturnJson returnJson;
        try {
            UserCustom user = userService.userLogin(name, password);
            returnJson = ReturnJson.success("登录成功");
            request.getSession().setAttribute(WebConst.ADMIN_SESSION_KEY, user);
        } catch (Exception e) {
            returnJson = ReturnJson.error(e.getMessage());
        }

        return returnJson;
    }

    @ResponseBody
    @PostMapping(value = "/logout")
    public ReturnJson logout(HttpSession session){
        session.removeAttribute(WebConst.ADMIN_SESSION_KEY);
        ReturnJson returnJson = ReturnJson.success("退出成功");
        return returnJson;
    }

}
