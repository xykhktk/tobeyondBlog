package com.tobeyond.blog.controller.admin;

import com.tobeyond.blog.model.Dto.ReturnJson;
import com.tobeyond.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller("adminIndexController")
@RequestMapping(value = "/admin")
public class IndexController {

    private ModelAndView modelAndView;

    @Autowired
    UserService userService;

    @GetMapping(value = {"","/","/index"})
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("/admin/index");
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
            userService.userLogin(name, password);
            returnJson = ReturnJson.success("登录成功");
        } catch (Exception e) {
            returnJson = ReturnJson.error(e.getMessage());
        }

//        int error_time = (int)request.getSession().getAttribute("login_error_times");

        return returnJson;
    }

}
