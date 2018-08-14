package com.tobeyond.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.model.po.Article;
import com.tobeyond.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("adminArticleController")
@RequestMapping(value = "/admin/article")
public class ArticleController {

    private ModelAndView modelAndView;

    @Autowired
    ArticleService articleService;

    @GetMapping(value = "/list/page/{page}")
    public ModelAndView articleList(@PathVariable int page){
        PageInfo<Article> articlesPaginator =  articleService.articleListBaseInfo(page,20);
        ModelAndView modelAndView = new ModelAndView("/admin/article/list");
        modelAndView.addObject("articleList",articlesPaginator);
        return  modelAndView;
    }

    @GetMapping(value = "/list")
    public ModelAndView articleList2(){
        PageInfo<Article> articlesPaginator =  articleService.articleListBaseInfo(1,10);
        ModelAndView modelAndView = new ModelAndView("/admin/article/list");
        modelAndView.addObject("articleList",articlesPaginator);
        return  modelAndView;
    }

}
