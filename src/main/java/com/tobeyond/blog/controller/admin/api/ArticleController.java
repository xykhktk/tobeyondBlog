package com.tobeyond.blog.controller.admin.api;

import com.auth0.jwt.JWT;
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
public class ArticleController extends BaseController{

    @Autowired
    QiniuConfig qiniuConfig;

    @Autowired
    IArticleService articleService;

    @Autowired
    ITagService tagService;

    @AdminLoginToken
    @PostMapping(value = "/list")
    public ReturnJson articleList(@RequestParam(value = "page",required = false) Integer page){
        PageInfo<ArticleBo> articlesPaginator =  articleService.articleListBaseInfo(page,10,null,false);
        returnData.put("data",articlesPaginator);
        returnJson.setData(returnData);
        return  returnJson;
    }

    @AdminLoginToken
    @PostMapping(value = "/addPage")
    public ReturnJson articleAdd(){
        List<TagPo> tagList = tagService.selectByExample(null);
        returnData.put("tagList",tagList);
        returnJson.setData(returnData);
        return  returnJson;
    }

    @AdminLoginToken
    @PostMapping(value = "/add")
    public ReturnJson articleAddSave(@RequestParam(value = "title",required = true) String title,
                                     @RequestParam(value = "subtitle",required = true) String subtitle,
                                     @RequestParam(value = "text",required = true) String content,
                                     @RequestParam(value = "is_show") Integer is_show,
                                     @RequestParam(value = "page_image",required = true) String page_image,
                                     @RequestParam(value = "tagIds")String tagIds,
                                     HttpServletRequest request){
        String token = request.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        ArticlePo article = new ArticlePo();
        article.setUser_id(Integer.valueOf(userId));
        article.setContent(content);
        article.setTitle(title);
        article.setSubtitle(subtitle);
        article.setPage_image(page_image);

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

    @PostMapping(value = "/editPage/{id}")
    public ReturnJson articleEdit(@PathVariable Long id){
        ArticleBo articleBo = articleService.articleFullInfo(id);
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(articleBo,articleVo);
        articleVo.setPageImgFull(qiniuConfig.getPath() +articleVo.getPage_image());

        List<TagPo> tagPoList = tagService.selectByExample(null);
        List<TagBo> tagBoList = new ArrayList<>();
        for(TagPo tagPo : tagPoList){
            TagBo tagBo = new TagBo();
            BeanUtils.copyProperties(tagPo,tagBo);
            tagBoList.add(tagBo);
        }

        for(TagBo tagBo : tagBoList){
            tagBo.setIs_selected(false);
            for (ArticleTagBo articleTagBo :articleBo.getTagList()){
                if(Objects.equals(articleTagBo.getTag_id(), tagBo.getId())){
                    tagBo.setIs_selected(true);
                }
            }
        }
        returnData.put("article",articleVo);
        returnData.put("tagList",tagBoList);
        return  returnJson;
    }
}
