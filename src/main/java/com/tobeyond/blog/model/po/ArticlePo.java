package com.tobeyond.blog.model.po;

import java.util.Date;

public class ArticlePo {
    private Integer id;

    private Integer categoryId;

    private Integer userId;

    private String title;

    private String subtitle;

    private String pageImage;

    private String metaDescription;

    private Byte isShow;

    private Byte isDel;

    private Integer viewCount;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    private String content;

    public ArticlePo(Integer id, Integer categoryId, Integer userId, String title, String subtitle, String pageImage, String metaDescription, Byte isShow, Byte isDel, Integer viewCount, Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.categoryId = categoryId;
        this.userId = userId;
        this.title = title;
        this.subtitle = subtitle;
        this.pageImage = pageImage;
        this.metaDescription = metaDescription;
        this.isShow = isShow;
        this.isDel = isDel;
        this.viewCount = viewCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public ArticlePo(Integer id, Integer categoryId, Integer userId, String title, String subtitle, String pageImage, String metaDescription, Byte isShow, Byte isDel, Integer viewCount, Date createdAt, Date updatedAt, Date deletedAt, String content) {
        this.id = id;
        this.categoryId = categoryId;
        this.userId = userId;
        this.title = title;
        this.subtitle = subtitle;
        this.pageImage = pageImage;
        this.metaDescription = metaDescription;
        this.isShow = isShow;
        this.isDel = isDel;
        this.viewCount = viewCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.content = content;
    }

    public ArticlePo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle == null ? null : subtitle.trim();
    }

    public String getPageImage() {
        return pageImage;
    }

    public void setPageImage(String pageImage) {
        this.pageImage = pageImage == null ? null : pageImage.trim();
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription == null ? null : metaDescription.trim();
    }

    public Byte getIsShow() {
        return isShow;
    }

    public void setIsShow(Byte isShow) {
        this.isShow = isShow;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}