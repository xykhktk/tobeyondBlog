package com.tobeyond.blog.model.Bo;

import com.tobeyond.blog.model.po.ArticlePo;

import java.util.ArrayList;

public class ArticleBo extends ArticlePo {

    private ArticlePo article;
    private ArrayList<ArticleTagBo> tagList;

    public ArticlePo getArticle() {
        return article;
    }

    public void setArticle(ArticlePo article) {
        this.article = article;
    }

    public ArrayList<ArticleTagBo> getTagList() {
        return tagList;
    }

    public void setTagList(ArrayList<ArticleTagBo> tagList) {
        this.tagList = tagList;
    }
}
