package com.tobeyond.blog.model.Bo;

import com.tobeyond.blog.model.po.ArticlePo;
import com.tobeyond.blog.model.po.Tag;

import java.util.List;

public class ArticleBo extends ArticlePo {

    private ArticlePo article;
    private List<Tag> tagList;

    public ArticlePo getArticle() {
        return article;
    }

    public void setArticle(ArticlePo article) {
        this.article = article;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }
}
