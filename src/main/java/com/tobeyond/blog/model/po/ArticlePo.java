package com.tobeyond.blog.model.po;

import java.util.List;

public class ArticlePo extends CommField {

    private Integer id;
    private Integer article_id;
    private Integer category_id;
    private Integer view_count;
    private Integer user_id;
    private String title;
    private String page_image;
    private String subtitle;
    private String content;
    private String slug;
//    private Integer is_original;
    private Integer is_show;
    private List<ArticleTagsPo> articleTag;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPage_image() {
        return page_image;
    }

    public void setPage_image(String page_image) {
        this.page_image = page_image;
    }

    public List<ArticleTagsPo> getArticleTag() {
        return articleTag;
    }

    public void setArticleTag(List<ArticleTagsPo> articleTag) {
        this.articleTag = articleTag;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getView_count() {
        return view_count;
    }

    public void setView_count(Integer view_count) {
        this.view_count = view_count;
    }

    public Integer getIs_show() {
        return is_show;
    }

    public void setIs_show(Integer is_show) {
        this.is_show = is_show;
    }

    @Override
    public String toString() {
        return "article_id:" + article_id + ",category_id:" + category_id + ",title:" + title + ",articleTag:";
    }
}
