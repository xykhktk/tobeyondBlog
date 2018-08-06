package com.tobeyond.blog.controller;


import com.tobeyond.blog.dao.domain.Article;
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

//        StringBuffer in_ids = new StringBuffer("");
//        if(null != tag_id){
//            List<ArticleTag> articleTags = articleService.getTagListByTagId(tag_id);
//            if(articleTags.size() > 0){
//                in_ids.append("(");
//                for(ArticleTag articleTag : articleTags){
//                    in_ids.append(articleTag.getArticle_id());
//                    in_ids.append(",");
//                }
//                in_ids.delete(in_ids.length() - 1,in_ids.length());
//                in_ids.append(")");
//            }else{
//                in_ids.append("(0)");
//            }
//        }

//        List<Article> articleList = articleService.articleList(in_ids.toString());
        List<Article> articleList = articleService.articleList(tag_id);
        ModelAndView modelAndView = new ModelAndView("/articleList");
        modelAndView.addObject("articleList",articleList);
        return modelAndView;
    }

    @RequestMapping("/articleDetail/{id}")
    public ModelAndView articleDetail(@PathVariable Long id){
        Article article = articleService.getArticleById(id);
        ModelAndView modelAndView = new ModelAndView("/articleDetail");
        modelAndView.addObject("article",article);
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping("/test")
    private  List<Article> text(){
        Long test0 = new Long(0);
        List<Article> articleList = articleService.articleList(test0);
        return articleList;
    }

}
