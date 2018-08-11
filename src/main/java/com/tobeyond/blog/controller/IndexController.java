package com.tobeyond.blog.controller;

import com.tobeyond.blog.dao.domain.Article;
import com.tobeyond.blog.dao.domain.Maxim;
import com.tobeyond.blog.model.Dto.ReturnJson;
import com.tobeyond.blog.service.ArticleService;
import com.tobeyond.blog.service.MaximsService;
import com.tobeyond.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    ArticleService articleService;

    @Autowired
    MaximsService maximsService;

    @Autowired
    MessageService messageService;

    private ModelAndView modelAndView;

    @RequestMapping("/show")
    public String getIndex(){
        return "index";
    }

    @RequestMapping("/")
    public ModelAndView index(){
        List<Article> articleList = articleService.articleListForIndex();
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