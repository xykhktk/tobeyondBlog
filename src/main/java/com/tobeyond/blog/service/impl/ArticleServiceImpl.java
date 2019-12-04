package com.tobeyond.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.config.QiniuConfig;
import com.tobeyond.blog.dao.mapper.TagMapper;
import com.tobeyond.blog.model.bo.ArticleBo;
import com.tobeyond.blog.model.bo.ArticleTagBo;
import com.tobeyond.blog.model.po.*;
import com.tobeyond.blog.dao.mapper.ArticleMapper;
import com.tobeyond.blog.dao.mapper.ArticleTagsMapper;
import com.tobeyond.blog.service.IArticleService;
import com.tobeyond.blog.util.DateKit;
//import org.markdownj.MarkdownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    QiniuConfig qiniuConfig;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleTagsMapper articleTagsMapper;
    @Autowired
    TagMapper tagMapper;

    @Override
    public List<ArticleTagsPo> getTagListByTagId(Long tag_id) {
        return articleTagsMapper.getTagsListByTagId(tag_id);
    }

    @Override
    public PageInfo<ArticleBo> articleListBaseInfo(Integer page, Integer limit, Long tag_id,Boolean is_show) {
        if(page == null) page = 1;

        List<String> inIds = null;
        if(tag_id != null){
            List<ArticleTagsPo> articleTags = getTagListByTagId(tag_id);
            if(articleTags.size() > 0){
                inIds = new ArrayList<>();
                for(ArticleTagsPo articleTag : articleTags){
                    inIds.add(String.valueOf(articleTag.getArticleId()));
                }
            }
        }
        HashMap<String,Object> params= new HashMap<>();
        params.put("inIds",inIds);

        if(is_show) params.put("is_show",'1');

        PageHelper.startPage(page, limit);
        List<ArticleBo> articleList = articleMapper.articleListBaseInfo(params);
        for(ArticleBo bo :articleList){
            bo.setPageImage(qiniuConfig.getPath() +  bo.getPageImage());
        }

        return new PageInfo<>(articleList);
    }

    @Override
    public ArticleBo articleFullInfo(Long id) {
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("id",id);
        ArticleBo articleBo = articleMapper.articleFullInfo(paramMap);
        ArrayList<ArticleTagBo> articleTagBo =  articleMapper.getArticleTags(articleBo.getId());
        articleBo.setTagList(articleTagBo);
        return articleBo;
    }

    @Override
    @Transactional
    public Boolean articleAdd(ArticlePo article,String tagIds) {
        String now = DateKit.dateFormat(new Date());
        Date dateNow = DateKit.getNow();
        try {
            article.setCreatedAt(now); //article的bean是自己写的,date字段设置为String了。有点不规范。
            article.setUpdatedAt(now);
            articleMapper.articleAdd(article);

            //批量插入
            String[] tagArray =  tagIds.split(",");
            List<ArticleTagsPo> articleTagPoList = new ArrayList<>();
            for(String tagId : tagArray){
                ArticleTagsPo articleTagPo = new ArticleTagsPo();
                articleTagPo.setArticleId(article.getId());
                articleTagPo.setTagId(Integer.valueOf(tagId));
                articleTagPo.setCreatedAt(dateNow); //代码生成器生成的代码是Date类型
                articleTagPo.setUpdatedAt(dateNow);
                articleTagPoList.add(articleTagPo);
            }
            articleTagsMapper.insertBatch(articleTagPoList);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public Boolean articleDel(Integer id) {
        try {
            articleMapper.articleDel(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Boolean changeShow(Integer id, Integer is_show) {
        try {
            HashMap<String,Object> params = new HashMap<>();
            params.put("id",id);
            params.put("is_show",is_show);
            articleMapper.changeShow(params);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Boolean articleEditSave(ArticlePo article, String tagIds) {
        String now = DateKit.dateFormat(new Date());
        Date dateNow = DateKit.getNow();

        try {
            article.setUpdatedAt(now);
            articleMapper.articleUpdate(article);
            ArrayList<ArticleTagBo> articleTagBo =  articleMapper.getArticleTags(article.getId());

            //求并集 交集 差级 https://blog.csdn.net/u011595939/article/details/74348216/
            List<String> selectedTag = new ArrayList<>();   //页面上选择的tag
            List<String> dbTag = new ArrayList<>(); //数据库里记录的tag
//            for(String selectTag : selectTags){ newTag.add(selectTag); }
            selectedTag.addAll(Arrays.asList(tagIds.split(",")));
            for(ArticleTagBo tag : articleTagBo){ dbTag.add(String.valueOf(tag.getTagId())); }

            List<String> insertTag = new ArrayList<>(selectedTag);   //要新增的tag
            insertTag.removeAll(dbTag);
            List<String> delTag = new ArrayList<>(dbTag);   //要删除的tag
            delTag.removeAll(selectedTag);

            if(insertTag.size() > 0){
                List<ArticleTagsPo> articleTagPoList = new ArrayList<>();
                for(String insertTagId : insertTag){
                    ArticleTagsPo articleTagPo = new ArticleTagsPo();
                    articleTagPo.setArticleId(article.getId());
                    articleTagPo.setTagId(Integer.valueOf(insertTagId));
                    articleTagPo.setCreatedAt(dateNow);
                    articleTagPo.setUpdatedAt(dateNow);
                    articleTagPoList.add(articleTagPo);
                }
                articleTagsMapper.insertBatch(articleTagPoList);
            }

            if(delTag.size() > 0){
                HashMap<String,Object> params = new HashMap<>();
                params.put("article_id",article.getId());
                params.put("whereInTagIds",delTag); //在mapper试使用where in，collection="whereInTagIds"要与这里对应
                params.put("deleted_at",now);
                articleTagsMapper.delArticleTags(params);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    // 其实是  select * from tags as t left join article_tags as at on t.id = at.tagId
    // where at.article_id = XXX and at.deleteAt is not null and ...
    @Override
    public List<TagPo> getTagListByArticleId(Integer id) {
        ArticleTagsExample articleTagsExample = new ArticleTagsExample();
        articleTagsExample.createCriteria().andArticleIdEqualTo(id).andDeletedAtIsNull();
        List<ArticleTagsPo> articleTagsPos = articleTagsMapper.selectByExample(articleTagsExample);

        TagExample tagExample = new TagExample();
        List<Integer> inIds = new ArrayList<>();
        for(ArticleTagsPo articleTagsPo : articleTagsPos){
            inIds.add(articleTagsPo.getTagId());
        }
        TagExample.Criteria criteria = tagExample.createCriteria().andDeletedAtIsNull();
        if(inIds.size() > 0)  criteria.andIdIn(inIds);
        return tagMapper.selectByExample(tagExample);
    }


}
