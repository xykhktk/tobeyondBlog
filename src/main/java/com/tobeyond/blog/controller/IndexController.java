package com.tobeyond.blog.controller;

import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.model.Bo.ArticleBo;
import com.tobeyond.blog.model.po.ArticlePo;
import com.tobeyond.blog.model.po.Maxim;
import com.tobeyond.blog.model.Dto.ReturnJson;
import com.tobeyond.blog.service.IArticleService;
import com.tobeyond.blog.service.IMaximsService;
import com.tobeyond.blog.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    IArticleService articleService;

    @Autowired
    IMaximsService maximsService;

    @Autowired
    IMessageService messageService;

    private ModelAndView modelAndView;

    @RequestMapping("/show")
    public String getIndex(){
        return "index";
    }

    @RequestMapping("/")
    public ModelAndView index(){
        PageInfo<ArticleBo> articlesPaginator = articleService.articleListBaseInfo(1,4,null,true);
        List<ArticleBo> articleList = articlesPaginator.getList();
        List<Maxim> maximList = maximsService.maximList();
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("articleList",articleList);
        modelAndView.addObject("maximList",maximList);
        return modelAndView;
    }

    @RequestMapping("/contact")
    public ModelAndView message(){
        modelAndView = new ModelAndView("/contact");
        return modelAndView;
    }

    @RequestMapping("/about")
    public ModelAndView about(){
        modelAndView = new ModelAndView("/about");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/leaveMessage",method = RequestMethod.POST)
    public ReturnJson leaveMessage(@RequestParam(value = "name",required = true) String name,
                                   @RequestParam(value = "message",required = true) String message,
                                   @RequestParam(value = "contact",required = true) String contact){

        return  messageService.insertMessage(name,message,contact);
    }

}
