package com.tobeyond.blog.model.Bo;

import com.tobeyond.blog.model.po.ArticleTagPo;
import com.tobeyond.blog.model.po.TagPo;

public class ArticleTagBo extends ArticleTagPo {

    private Integer id;
    private Integer tag_id;
    private Integer article_id;
    //可直接在select里面写left join。例如 select article_tag.tag_id,tag.title from article_tag left join tag ........
    private String title;
    private TagPo tag;

    public TagPo getTag() {
        return tag;
    }

    public void setTag(TagPo tag) {
        this.tag = tag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTag_id() {
        return tag_id;
    }

    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
    }

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
