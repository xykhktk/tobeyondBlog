package com.tobeyond.blog.model.bo;

import com.tobeyond.blog.model.po.ArticleTagsPo;
import com.tobeyond.blog.model.po.TagPo;

public class ArticleTagBo extends ArticleTagsPo {

    //对应mapper的
    //可直接在select里面写left join。例如 select article_tag.tag_id,tag.title from article_tag left join tag ........
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
