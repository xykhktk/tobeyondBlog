package com.tobeyond.blog.model.bo;

import com.tobeyond.blog.model.po.ArticlePo;

import java.util.ArrayList;

public class ArticleBo extends ArticlePo {

    private ArrayList<ArticleTagBo> tagList;

    public ArrayList<ArticleTagBo> getTagList() {
        return tagList;
    }

    public void setTagList(ArrayList<ArticleTagBo> tagList) {
        this.tagList = tagList;
    }
}
