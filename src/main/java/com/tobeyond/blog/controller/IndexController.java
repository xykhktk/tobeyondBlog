package com.tobeyond.blog.controller;

import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.model.bo.ArticleBo;
import com.tobeyond.blog.model.dto.ReturnJson;
import com.tobeyond.blog.model.po.MaximExample;
import com.tobeyond.blog.model.po.MaximPo;
import com.tobeyond.blog.service.IArticleService;
import com.tobeyond.blog.service.IMaximsService;
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

    private ModelAndView modelAndView;

    @RequestMapping("/show")
    public String getIndex(){
        return "index";
    }

    @RequestMapping("/")
    public ModelAndView index(){
        PageInfo<ArticleBo> articlesPaginator = articleService.articleList(1,4,null,Byte.valueOf("1"));
        List<ArticleBo> articleList = articlesPaginator.getList();

//        List<Maxim> maximList = maximsService.maximList();
        MaximExample maximExample = new MaximExample();
        maximExample.createCriteria().andIsShowEqualTo(Byte.valueOf("1"));
        List<MaximPo> maximList = maximsService.selectByExample(maximExample);

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

//    @ResponseBody
//    @RequestMapping(value = "/leaveMessage",method = RequestMethod.POST)
//    public ReturnJson leaveMessage(@RequestParam(value = "name",required = true) String name,
//                                   @RequestParam(value = "message",required = true) String message,
//                                   @RequestParam(value = "contact",required = true) String contact){
//
//        return  messageService.insertMessage(name,message,contact);
//    }

}
