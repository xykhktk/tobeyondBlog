package com.tobeyond.blog.model.po;

import java.util.Date;

public class ArticleTagsPo {
    private Integer id;

    private Integer tagId;

    private Integer articleId;

    private Byte isDel;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    public ArticleTagsPo(Integer id, Integer tagId, Integer articleId, Byte isDel, Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.tagId = tagId;
        this.articleId = articleId;
        this.isDel = isDel;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public ArticleTagsPo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}