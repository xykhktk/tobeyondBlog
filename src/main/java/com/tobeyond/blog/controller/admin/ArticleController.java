package com.tobeyond.blog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.model.Bo.ArticleBo;
import com.tobeyond.blog.model.Bo.UserCustom;
import com.tobeyond.blog.model.Dto.ReturnJson;
import com.tobeyond.blog.model.po.ArticlePo;
import com.tobeyond.blog.model.po.Tag;
import com.tobeyond.blog.service.ArticleService;
import com.tobeyond.blog.service.TagService;
import com.tobeyond.blog.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("adminArticleController")
@RequestMapping(value = "/admin/article")
public class ArticleController {

    private ModelAndView modelAndView;

    @Autowired
    ArticleService articleService;

    @Autowired
    TagService tagService;

//    @GetMapping(value = "/list/page/{page}")
//    public ModelAndView articleList(@PathVariable int page){
//        PageInfo<Article> articlesPaginator =  articleService.articleListBaseInfo(page,20);
//        ModelAndView modelAndView = new ModelAndView("/admin/article/list");
//        modelAndView.addObject("articleList",articlesPaginator);
//        return  modelAndView;
//    }

    @GetMapping(value = "/list")
    public ModelAndView articleList(@RequestParam(value = "page",required = false) Integer page){
        PageInfo<ArticlePo> articlesPaginator =  articleService.articleListBaseInfo(page,10);
        ModelAndView modelAndView = new ModelAndView("/admin/article/list");
        modelAndView.addObject("articleList",articlesPaginator);
        return  modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView articleAdd(){
        List<Tag> tagList = tagService.tagList();
        System.out.print(JSON.toJSONString(tagList));
        ModelAndView modelAndView = new ModelAndView("/admin/article/add");
        modelAndView.addObject("tagList",tagList);
        return  modelAndView;
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public ReturnJson articleAddSave(@RequestParam(value = "title",required = true) String title,
                                       @RequestParam(value = "subtitle",required = true) String subtitle,
                                       @RequestParam(value = "slug",required = true) String slug,
                                       @RequestParam(value = "text",required = true) String content,
                                       @RequestParam(value = "is_show") Integer is_show,
                                       @RequestParam(value = "page_image",required = true) String page_image,
                                       @RequestParam(value = "tagIds")String tagIds,
                                       HttpServletRequest request){

        UserCustom user = CommonUtils.getLoginUser(request);
        ArticlePo article = new ArticlePo();
        article.setUser_id(user.getId());
        article.setContent(content);
        article.setTitle(title);
        article.setSubtitle(subtitle);
        article.setPage_image(page_image);
        article.setSlug(slug);

        if(is_show == null) is_show = 0;
        article.setIs_show(is_show);

        ReturnJson returnJson;
        try {
            articleService.articleAdd(article,tagIds);
            returnJson = ReturnJson.success("添加成功");
        } catch (Exception e) {
            returnJson = ReturnJson.error(e.getMessage());
        }

        return returnJson;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView articleEdit(@PathVariable Long id){
        ArticleBo article = articleService.articleFullInfo(id);
        ModelAndView modelAndView = new ModelAndView("/admin/article/edit");
        modelAndView.addObject("article",article);
        return  modelAndView;
    }

//    @GetMapping(valu/e = "/test")
//    public ModelAndView articleTest(){
//        ArticlePo a = new ArticlePo();
//        articleService.articleAdd(a);
//        ModelAndView modelAndView = new ModelAndView("/admin/article/add");
//        return  modelAndView;
//    }

}
