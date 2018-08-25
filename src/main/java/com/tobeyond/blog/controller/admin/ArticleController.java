package com.tobeyond.blog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.model.Bo.ArticleBo;
import com.tobeyond.blog.model.Bo.ArticleTagBo;
import com.tobeyond.blog.model.Bo.TagBo;
import com.tobeyond.blog.model.Bo.UserCustom;
import com.tobeyond.blog.model.Dto.ReturnJson;
import com.tobeyond.blog.model.po.ArticlePo;
import com.tobeyond.blog.model.po.TagPo;
import com.tobeyond.blog.service.IArticleService;
import com.tobeyond.blog.service.ITagService;
import com.tobeyond.blog.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller("adminArticleController")
@RequestMapping(value = "/admin/article")
public class ArticleController {

    private ModelAndView modelAndView;

    @Autowired
    IArticleService articleService;

    @Autowired
    ITagService tagService;

//    @GetMapping(value = "/list/page/{page}")
//    public ModelAndView articleList(@PathVariable int page){
//        PageInfo<Article> articlesPaginator =  articleService.articleListBaseInfo(page,20);
//        ModelAndView modelAndView = new ModelAndView("/admin/article/list");
//        modelAndView.addObject("articleList",articlesPaginator);
//        return  modelAndView;
//    }

    @GetMapping(value = "/list")
    public ModelAndView articleList(@RequestParam(value = "page",required = false) Integer page){
        PageInfo<ArticleBo> articlesPaginator =  articleService.articleListBaseInfo(page,10,null);
//        System.out.print(JSON.toJSONString(articlesPaginator.getList()));
        ModelAndView modelAndView = new ModelAndView("/admin/article/list");
        modelAndView.addObject("articleList",articlesPaginator);
        return  modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView articleAdd(){
        List<TagBo> tagList = tagService.tagList();
//        System.out.print(JSON.toJSONString(tagList));
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
            Boolean res = articleService.articleAdd(article,tagIds);
            if(res){
                returnJson = ReturnJson.success("添加成功");
            }else{
                returnJson = ReturnJson.error("添加失败");
            }
        } catch (Exception e) {
            returnJson = ReturnJson.error(e.getMessage());
        }

        return returnJson;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView articleEdit(@PathVariable Long id){
        ArticleBo article = articleService.articleFullInfo(id);
        List<TagBo> tagList = tagService.tagList();
        for(TagBo tagBo : tagList){
            tagBo.setIs_selected(false);
            for (ArticleTagBo articleTagBo :article.getTagList()){
                if(Objects.equals(articleTagBo.getTag_id(), tagBo.getId())){
                    tagBo.setIs_selected(true);
                }
            }
        }
        ModelAndView modelAndView = new ModelAndView("/admin/article/edit");
        System.out.print(JSON.toJSONString(tagList));
        modelAndView.addObject("article",article);
        modelAndView.addObject("tagList",tagList);
        return  modelAndView;
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public ReturnJson articleEditSave(@RequestParam(value = "id",required = true) Integer id,
                                      @RequestParam(value = "title",required = true) String title,
                                      @RequestParam(value = "subtitle",required = true) String subtitle,
                                      @RequestParam(value = "slug",required = true) String slug,
                                      @RequestParam(value = "text",required = true) String content,
                                      @RequestParam(value = "is_show") Integer is_show,
                                      @RequestParam(value = "page_image",required = true) String page_image,
                                      @RequestParam(value = "tagIds")String tagIds,
                                      HttpServletRequest request){

        ArticlePo article = new ArticlePo();
        article.setId(id);
        article.setContent(content);
        article.setTitle(title);
        article.setSubtitle(subtitle);
        article.setPage_image(page_image);
        article.setSlug(slug);

        ReturnJson returnJson;
        try {
            if(articleService.articleEditSave(article,tagIds)){
                returnJson = ReturnJson.success("添加成功");
            }else{
                returnJson = ReturnJson.error("添加失败");
            }
        } catch (Exception e) {
            returnJson = ReturnJson.error(e.getMessage());
        }

        return returnJson;
    }

    @PostMapping(value = "/del")
    @ResponseBody
    public ReturnJson articleDel(@RequestParam(value = "id",required = true) Integer id,
                                     HttpServletRequest request){
        ReturnJson returnJson;
        try {
            articleService.articleDel(id);
            returnJson = ReturnJson.success("删除成功");
        } catch (Exception e) {
            returnJson = ReturnJson.error(e.getMessage());
        }

        return returnJson;
    }

    @PostMapping(value = "/changeShow")
    @ResponseBody
    public ReturnJson changeShow(@RequestParam(value = "id",required = true) Integer id,
                                 @RequestParam(value = "is_show",required = true) Integer is_show,
                                 HttpServletRequest request){
        ReturnJson returnJson;
        try {
            articleService.changeShow(id,is_show);
            returnJson = ReturnJson.success("设置成功");
        } catch (Exception e) {
            returnJson = ReturnJson.error(e.getMessage());
        }

        return returnJson;
    }

//    @GetMapping(valu/e = "/test")
//    public ModelAndView articleTest(){
//        ArticlePo a = new ArticlePo();
//        articleService.articleAdd(a);
//        ModelAndView modelAndView = new ModelAndView("/admin/article/add");
//        return  modelAndView;
//    }

}
