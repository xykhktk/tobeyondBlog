package com.tobeyond.blog.controller;


import com.tobeyond.blog.model.po.ArticlePo;
import com.tobeyond.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;


    @RequestMapping(value = "/articleList" , method = RequestMethod.GET)
    public ModelAndView articleList(@RequestParam(value = "tag_id",required = false) Long tag_id){

        List<ArticlePo> articleList = articleService.articleList(tag_id);
        ModelAndView modelAndView = new ModelAndView("/articleList");
        modelAndView.addObject("articleList",articleList);
        return modelAndView;
    }

    @RequestMapping("/articleDetail/{id}")
    public ModelAndView articleDetail(@PathVariable Long id){
        ArticlePo article = articleService.getArticleById(id);
        ModelAndView modelAndView = new ModelAndView("/articleDetail");
        modelAndView.addObject("article",article);
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping("/test")
    private  List<ArticlePo> text(){
        Long test0 = new Long(0);
        List<ArticlePo> articleList = articleService.articleList(test0);
        return articleList;
    }

}
