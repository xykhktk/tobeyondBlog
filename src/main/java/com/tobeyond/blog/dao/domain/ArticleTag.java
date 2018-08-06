package com.tobeyond.blog.dao.domain;

public class ArticleTag extends CommField {

    private Integer id;
    private Integer tag_id;
    private Integer article_id;
    private Tag tag;

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
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

    @Override
    public String toString() {
        return "id:" + id + ",tag_id:" + tag_id + ",article_id:" + article_id ;
    }
}
