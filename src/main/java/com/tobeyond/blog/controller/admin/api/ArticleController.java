package com.tobeyond.blog.controller.admin.api;

import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.annotation.AdminLoginToken;
import com.tobeyond.blog.config.QiniuConfig;
import com.tobeyond.blog.model.Bo.ArticleBo;
import com.tobeyond.blog.model.Bo.ArticleTagBo;
import com.tobeyond.blog.model.Bo.TagBo;
import com.tobeyond.blog.model.Bo.UserCustom;
import com.tobeyond.blog.model.Dto.ReturnJson;
import com.tobeyond.blog.model.Vo.ArticleVo;
import com.tobeyond.blog.model.po.ArticlePo;
import com.tobeyond.blog.model.po.TagPo;
import com.tobeyond.blog.service.IArticleService;
import com.tobeyond.blog.service.ITagService;
import com.tobeyond.blog.util.CommonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController("apiAdminArticleController")
@RequestMapping(value = "/api/admin/article")
public class ArticleController {

    private ModelAndView modelAndView;

    @Autowired
    QiniuConfig qiniuConfig;

    @Autowired
    IArticleService articleService;

    @Autowired
    ITagService tagService;

    @AdminLoginToken
    @PostMapping(value = "/list")
    public ReturnJson articleList(@RequestParam(value = "page",required = false) Integer page){
        ReturnJson returnJson = ReturnJson.success("获取文章列表成功");;
        PageInfo<ArticleBo> articlesPaginator =  articleService.articleListBaseInfo(page,10,null,false);
        Map data = new HashMap<String,String>();
        data.put("data",articlesPaginator);
        returnJson.setData(data);
        return  returnJson;
    }

}
