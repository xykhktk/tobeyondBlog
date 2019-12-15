package com.tobeyond.blog.controller.admin.pc;

import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.config.QiniuConfig;
import com.tobeyond.blog.model.bo.ArticleBo;
import com.tobeyond.blog.model.bo.ArticleTagBo;
import com.tobeyond.blog.model.bo.TagBo;
import com.tobeyond.blog.model.bo.UserCustom;
import com.tobeyond.blog.model.dto.ReturnJson;
import com.tobeyond.blog.model.vo.ArticleVo;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller("adminArticleController")
@RequestMapping(value = "/admin/article")
public class ArticleController {

    private ModelAndView modelAndView;

    @Autowired
    QiniuConfig qiniuConfig;

    @Autowired
    IArticleService articleService;

    @Autowired
    ITagService tagService;

    @GetMapping(value = "/list")
    public ModelAndView articleList(@RequestParam(value = "page",required = false) Integer page){
        PageInfo<ArticleBo> articlesPaginator =  articleService.articleList(page,10,null,Byte.valueOf("1"));
        ModelAndView modelAndView = new ModelAndView("/admin/article/list");
        modelAndView.addObject("articleList",articlesPaginator);
        return  modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView articleAdd(){
        List<TagPo> tagList = tagService.selectByExample(null);
        ModelAndView modelAndView = new ModelAndView("/admin/article/add");
        modelAndView.addObject("tagList",tagList);
        return  modelAndView;
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public ReturnJson articleAddSave(@RequestParam(value = "title",required = true) String title,
                                     @RequestParam(value = "subtitle",required = true) String subtitle,
                                     @RequestParam(value = "text",required = true) String content,
                                     @RequestParam(value = "is_show") Byte isShow,
                                     @RequestParam(value = "page_image",required = true) String page_image,
                                     @RequestParam(value = "tagIds")String tagIds,
                                     HttpServletRequest request){

        UserCustom user = CommonUtils.getLoginUser(request);
        ArticlePo article = new ArticlePo();
        article.setUserId(user.getId());
        article.setContent(content);
        article.setTitle(title);
        article.setSubtitle(subtitle);
        article.setPageImage(page_image);

        if(isShow == null) isShow = 1;
        article.setIsShow(isShow);

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
    public ModelAndView articleEdit(@PathVariable Integer id){
        ArticleBo articleBo = articleService.articleDetail(id);
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(articleBo,articleVo);
        articleVo.setPageImgFull(qiniuConfig.getPath() +articleVo.getPageImage());

        List<TagPo> tagPoList = tagService.selectByExample(null);

//需要优化
        List<TagBo> tagBoList = new ArrayList<>();
        for(TagPo tagPo : tagPoList){
            TagBo tagBo = new TagBo();
            tagBo.setIs_selected(false);
            tagBo.setId(tagPo.getId());
            tagBo.setUpdatedAt(tagPo.getUpdatedAt());
            tagBo.setTitle(tagPo.getTitle());
            tagBo.setTag(tagPo.getTag());
            tagBo.setCreatedAt(tagPo.getCreatedAt());
            tagBoList.add(tagBo);
        }

        for(TagBo tagBo : tagBoList){
            tagBo.setIs_selected(false);
            for (ArticleTagBo articleTagBo :articleBo.getTagList()){
                if(Objects.equals(articleTagBo.getTagId(), tagBo.getId())){
                    tagBo.setIs_selected(true);
                }
            }
        }
        ModelAndView modelAndView = new ModelAndView("/admin/article/edit");
        modelAndView.addObject("article",articleVo);
        modelAndView.addObject("tagList",tagBoList);
        return  modelAndView;
    }

    @PostMapping(value = "/edit/{id}")
    @ResponseBody
    public ReturnJson articleEditSave(@RequestParam(value = "id") Integer id,
                                      @RequestParam(value = "title") String title,
                                      @RequestParam(value = "subtitle") String subtitle,
                                      @RequestParam(value = "text") String content,
                                      @RequestParam(value = "isShow",required = false) Byte isShow,
                                      @RequestParam(value = "page_image") String page_image,
                                      @RequestParam(value = "tagIds")String tagIds,
                                      HttpServletRequest request){

        ArticlePo article = new ArticlePo();
        article.setId(id);
        article.setContent(content);
        article.setTitle(title);
        article.setSubtitle(subtitle);
        article.setPageImage(page_image);
        article.setIsShow(isShow);

        ReturnJson returnJson;
        try {
            if(articleService.articleEditSave(article,tagIds)){
                returnJson = ReturnJson.success("修改成功");
            }else{
                returnJson = ReturnJson.error("修改失败");
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
                                 @RequestParam(value = "is_show",required = true) Byte isShow,
                                 HttpServletRequest request){
        ReturnJson returnJson;
        try {
            articleService.changeShow(id,isShow);
            returnJson = ReturnJson.success("设置成功");
        } catch (Exception e) {
            returnJson = ReturnJson.error(e.getMessage());
        }

        return returnJson;
    }

}
