package com.tobeyond.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tobeyond.blog.model.po.Article;
import com.tobeyond.blog.model.po.ArticleTag;
import com.tobeyond.blog.dao.mapper.ArticleMapper;
import com.tobeyond.blog.dao.mapper.ArticleTagMapper;
import com.tobeyond.blog.service.ArticleService;
import org.markdownj.MarkdownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleTagMapper articleTagMapper;

    @Override
    public List<Article> articleList(Long tag_id) {

        StringBuffer in_ids = new StringBuffer("");
        if(null != tag_id){
            List<ArticleTag> articleTags = getTagListByTagId(tag_id);
            if(articleTags.size() > 0){
                in_ids.append("(");
                for(ArticleTag articleTag : articleTags){
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
    public List<Article> articleListForIndex() {
        return articleMapper.articleListForIndex();
    }

    @Override
    public Article getArticleById(long id) {
        Article article =  articleMapper.getArticleById(id);
        JSONObject jsonObject = JSON.parseObject(article.getContent());
        MarkdownProcessor markdownProcessor = new MarkdownProcessor();
        String html = markdownProcessor.markdown(jsonObject.getString("raw"));
        article.setContent(html);
        return  article;
    }

    @Override
    public List<ArticleTag> getTagListByTagId(Long tag_id) {
        return articleTagMapper.getTagsListByTagId(tag_id);
    }
}
