package com.tobeyond.blog.controller;


import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.model.bo.ArticleBo;
import com.tobeyond.blog.model.dto.ListWithPager;
import com.tobeyond.blog.model.po.TagPo;
import com.tobeyond.blog.service.IArticleService;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ArticleController {

//    @Autowired
//    IArticleService articleService;
//
//
//    @RequestMapping(value = "/articleList", method = RequestMethod.GET)
//    public ModelAndView articleList(
//            @RequestParam(value = "page", required = false) Integer page,
//            @RequestParam(value = "tag_id", required = false) Integer tagId
//    ) {
//
//        ListWithPager listWithPager = articleService.articleList(page, 12, tagId,Byte.valueOf("1"));
//        ModelAndView modelAndView = new ModelAndView("/articleList");
//        modelAndView.addObject("articlesPaginator", listWithPager);
//        return modelAndView;
//    }
//
//    @RequestMapping("/articleDetail/{id}")
//    public ModelAndView articleDetail(@PathVariable Integer id){
//        ArticleBo article = articleService.articleDetail(id);
//        Parser parser = Parser.builder().build();
//        Node node = parser.parse(article.getContent());
//        HtmlRenderer renderer = HtmlRenderer.builder().build();
//        article.setContent( renderer.render(node));
//
//        List<TagPo> tagPoList =  articleService.getTagListByArticleId(id.intValue());
//
//        ModelAndView modelAndView = new ModelAndView("/articleDetail");
//        modelAndView.addObject("article",article);
//        modelAndView.addObject("tagList",tagPoList);
//        return modelAndView;
//    }

}
