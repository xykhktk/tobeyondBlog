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
import java.util.ArrayList;
import java.util.Arrays;
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
        PageInfo<ArticleBo> articlesPaginator =  articleService.articleListBaseInfo(page,10,null,false);
//        System.out.print(JSON.toJSONString(articlesPaginator.getList()));
        ModelAndView modelAndView = new ModelAndView("/admin/article/list");
        modelAndView.addObject("articleList",articlesPaginator);
        return  modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView articleAdd(){
        List<TagPo> tagList = tagService.selectByExample(null);
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
        List<TagPo> tagPoList = tagService.selectByExample(null);


//        Object[] object = tagPoList.toArray();
        //使用Arrays工具类，将数组转换成list
//        List<Object> objects = Arrays.asList(object);
        //将objects强转成childlist；这里强转时，不能定义后面括号内的List类型，如果定义会报编译错误
        //及时child没有继承Parent，这里也不会报编译错误，但是按照Child对象循环输出时会报错
//        List<TagBo> tagBoList = (List)objects;

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
            for (ArticleTagBo articleTagBo :article.getTagList()){
                if(Objects.equals(articleTagBo.getTag_id(), tagBo.getId())){
                    tagBo.setIs_selected(true);
                }
            }
        }
        ModelAndView modelAndView = new ModelAndView("/admin/article/edit");
        modelAndView.addObject("article",article);
        modelAndView.addObject("tagList",tagBoList);
        return  modelAndView;
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public ReturnJson articleEditSave(@RequestParam(value = "id") Integer id,
                                      @RequestParam(value = "title") String title,
                                      @RequestParam(value = "subtitle") String subtitle,
                                      @RequestParam(value = "slug") String slug,
                                      @RequestParam(value = "text") String content,
                                      @RequestParam(value = "is_show",required = false) Integer is_show,
                                      @RequestParam(value = "page_image") String page_image,
                                      @RequestParam(value = "tagIds")String tagIds,
                                      HttpServletRequest request){

        ArticlePo article = new ArticlePo();
        article.setId(id);
        article.setContent(content);
        article.setTitle(title);
        article.setSubtitle(subtitle);
        article.setPage_image(page_image);
        article.setSlug(slug);
        article.setIs_show(is_show);

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

}
