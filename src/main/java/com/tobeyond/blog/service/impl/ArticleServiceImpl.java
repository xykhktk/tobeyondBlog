package com.tobeyond.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.model.Bo.ArticleBo;
import com.tobeyond.blog.model.po.ArticlePo;
import com.tobeyond.blog.model.po.ArticleTagPo;
import com.tobeyond.blog.dao.mapper.ArticleMapper;
import com.tobeyond.blog.dao.mapper.ArticleTagMapper;
import com.tobeyond.blog.service.IArticleService;
import com.tobeyond.blog.util.DateKit;
import org.markdownj.MarkdownProcessor;
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
    public List<ArticlePo> articleList(Long tag_id) {

        StringBuffer in_ids = new StringBuffer("");
        if(null != tag_id){
            List<ArticleTagPo> articleTags = getTagListByTagId(tag_id);
            if(articleTags.size() > 0){
                in_ids.append("(");
                for(ArticleTagPo articleTag : articleTags){
                    in_ids.append(articleTag.getArticle_id());
                    in_ids.append(",");
                }
                in_ids.delete(in_ids.length() - 1,in_ids.length());
                in_ids.append(")");
            }else{
                in_ids.append("(0)");
            }
        }

        return articleMapper.articleList(in_ids.toString());
    }

    @Override
    public List<ArticlePo> articleListForIndex() {
        return articleMapper.articleListForIndex();
    }

    @Override
    public ArticlePo getArticleById(long id) {
        ArticlePo article =  articleMapper.getArticleById(id);
        JSONObject jsonObject = JSON.parseObject(article.getContent());
        MarkdownProcessor markdownProcessor = new MarkdownProcessor();
        String html = markdownProcessor.markdown(jsonObject.getString("raw"));
        article.setContent(html);
        return  article;
    }

    @Override
    public List<ArticleTagPo> getTagListByTagId(Long tag_id) {
        return articleTagMapper.getTagsListByTagId(tag_id);
    }

    @Override
    public PageInfo<ArticlePo> articleListBaseInfo(Integer page, Integer limit) {
        if(page == null) page = 1;
        PageHelper.startPage(page, limit);
        List<ArticlePo> articleList = articleMapper.articleListBaseInfo();
        return new PageInfo<>(articleList);
    }

    @Override
    public ArticleBo articleFullInfo(Long id) {
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("id",id);
        ArticleBo articleCustom = articleMapper.articleFullInfo(paramMap);
        return articleCustom;
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


}
