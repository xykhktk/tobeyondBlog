package com.tobeyond.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.model.Bo.ArticleBo;
import com.tobeyond.blog.model.Bo.ArticleTagBo;
import com.tobeyond.blog.model.po.ArticlePo;
import com.tobeyond.blog.model.po.ArticleTagPo;
import com.tobeyond.blog.dao.mapper.ArticleMapper;
import com.tobeyond.blog.dao.mapper.ArticleTagMapper;
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
    ArticleMapper articleMapper;
    @Autowired
    ArticleTagMapper articleTagMapper;

    @Override
    public List<ArticleTagPo> getTagListByTagId(Long tag_id) {
        return articleTagMapper.getTagsListByTagId(tag_id);
    }

    @Override
    public PageInfo<ArticleBo> articleListBaseInfo(Integer page, Integer limit, Long tag_id,Boolean is_show) {
        if(page == null) page = 1;

        List<String> inIds = null;
        if(tag_id != null){
            List<ArticleTagPo> articleTags = getTagListByTagId(tag_id);
            if(articleTags.size() > 0){
                inIds = new ArrayList<>();
                for(ArticleTagPo articleTag : articleTags){
                    inIds.add(String.valueOf(articleTag.getArticle_id()));
                }
            }
        }
        HashMap<String,Object> params= new HashMap<>();
        params.put("inIds",inIds);

        if(is_show) params.put("is_show",'1');

        PageHelper.startPage(page, limit);
        List<ArticleBo> articleList = articleMapper.articleListBaseInfo(params);
        return new PageInfo<>(articleList);
    }

    @Override
    public ArticleBo articleFullInfo(Long id) {
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("id",id);
        return articleMapper.articleFullInfo(paramMap);
    }

    @Override
    @Transactional
    public Boolean articleAdd(ArticlePo article,String tagIds) {
        String now = DateKit.dateFormat(new Date());
        try {
            article.setCreated_at(now);
            article.setUpdated_at(now);
            articleMapper.articleAdd(article);

            //批量插入
            String[] tagArray =  tagIds.split(",");
            List<ArticleTagPo> articleTagPoList = new ArrayList<>();
            for(String tagId : tagArray){
                ArticleTagPo articleTagPo = new ArticleTagPo();
                articleTagPo.setArticle_id(article.getId());
                articleTagPo.setTag_id(Integer.valueOf(tagId));
                articleTagPo.setCreated_at(now);
                articleTagPo.setUpdated_at(now);
                articleTagPoList.add(articleTagPo);
            }
            articleTagMapper.insertBatch(articleTagPoList);

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
        try {
            article.setUpdated_at(now);
            articleMapper.articleUpdate(article);
            ArrayList<ArticleTagBo> articleTagBo =  articleMapper.getArticleTags(article.getId());

            //求并集 交集 差级 https://blog.csdn.net/u011595939/article/details/74348216/
            List<String> selectedTag = new ArrayList<>();   //页面上选择的tag
            List<String> dbTag = new ArrayList<>(); //数据库里记录的tag
//            for(String selectTag : selectTags){ newTag.add(selectTag); }
            selectedTag.addAll(Arrays.asList(tagIds.split(",")));
            for(ArticleTagBo tag : articleTagBo){ dbTag.add(String.valueOf(tag.getTag_id())); }

            List<String> insertTag = new ArrayList<>(selectedTag);   //要新增的tag
            insertTag.removeAll(dbTag);
            List<String> delTag = new ArrayList<>(dbTag);   //要删除的tag
            delTag.removeAll(selectedTag);

            if(insertTag.size() > 0){
                List<ArticleTagPo> articleTagPoList = new ArrayList<>();
                for(String insertTagId : insertTag){
                    ArticleTagPo articleTagPo = new ArticleTagPo();
                    articleTagPo.setArticle_id(article.getId());
                    articleTagPo.setTag_id(Integer.valueOf(insertTagId));
                    articleTagPo.setCreated_at(now);
                    articleTagPo.setUpdated_at(now);
                    articleTagPoList.add(articleTagPo);
                }
                articleTagMapper.insertBatch(articleTagPoList);
            }

            if(delTag.size() > 0){
                HashMap<String,Object> params = new HashMap<>();
                params.put("article_id",article.getId());
                params.put("whereInTagIds",delTag); //在mapper试使用where in，collection="whereInTagIds"要与这里对应
                params.put("deleted_at",now);
                articleTagMapper.delArticleTags(params);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }


}
