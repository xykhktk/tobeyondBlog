package com.tobeyond.blog.model.po;

public class Tag {

    private Integer id;
    private String tag;
    private String title;
    private String meta_description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMeta_description() {
        return meta_description;
    }

    public void setMeta_description(String meta_description) {
        this.meta_description = meta_description;
    }

    @Override
    public String toString() {
        return "id:" + id + ",title:" + title;
    }
}
