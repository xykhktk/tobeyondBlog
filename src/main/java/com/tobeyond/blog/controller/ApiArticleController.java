package com.tobeyond.blog.controller;


import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.model.bo.ArticleBo;
import com.tobeyond.blog.model.dto.ListWithPager;
import com.tobeyond.blog.model.dto.ReturnJson;
import com.tobeyond.blog.model.po.TagPo;
import com.tobeyond.blog.service.IArticleService;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("apiArticleController")
@RequestMapping(value = "/api/article")
public class ApiArticleController extends ApiBaseController {

    @Autowired
    IArticleService articleService;

    @PostMapping(value = "/list")
    public ReturnJson articleList(@RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "tagId", required = false) Integer tagId ) {
        ListWithPager listWithPager = articleService.articleList(page, 12, tagId,Byte.valueOf("1"));

        returnData.put("data",listWithPager);
        return ReturnJson.success("获取数据成功",returnData);
    }

    @PostMapping("/detail")
    public ReturnJson articleDetail(@RequestParam(value = "page", required = true) Integer id){
        ArticleBo article = articleService.articleDetail(id);
        Parser parser = Parser.builder().build();
        Node node = parser.parse(article.getContent());
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        article.setContent( renderer.render(node));

        List<TagPo> tagPoList =  articleService.getTagListByArticleId(id);

        returnData.put("article",article);
        returnData.put("tagList",tagPoList);
        return ReturnJson.success("获取数据成功",returnData);
    }

}
