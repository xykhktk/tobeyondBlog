package com.tobeyond.blog.controller;

import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.model.bo.ArticleBo;
import com.tobeyond.blog.model.dto.ListWithPager;
import com.tobeyond.blog.model.dto.ReturnJson;
import com.tobeyond.blog.model.po.MaximExample;
import com.tobeyond.blog.model.po.MaximPo;
import com.tobeyond.blog.service.IArticleService;
import com.tobeyond.blog.service.IMaximsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("apiBlogController")
@RequestMapping(value = "/api/blog")
public class ApiIndexController extends ApiBaseController {

    @Autowired
    IArticleService articleService;

    @Autowired
    IMaximsService maximsService;

    @PostMapping("/home")
    public ReturnJson index(){
        ListWithPager listWithPager = articleService.articleList(1,4,null,Byte.valueOf("1"));
        List<ArticleBo> articleList = listWithPager.getList();

        MaximExample maximExample = new MaximExample();
        maximExample.createCriteria().andIsShowEqualTo(Byte.valueOf("1"));
        List<MaximPo> maximList = maximsService.selectByExample(maximExample);

        returnData.put("articleList",articleList);
        returnData.put("maximList",maximList);
        return ReturnJson.success("获取数据成功",returnData);
    }

}
