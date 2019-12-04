package com.tobeyond.blog.model.bo;

import com.tobeyond.blog.model.po.TagPo;

public class TagBo extends TagPo {

    private Boolean is_selected;    //文章是否选择了这个tag

    public Boolean getIs_selected() {
        return is_selected;
    }

    public void setIs_selected(Boolean is_selected) {
        this.is_selected = is_selected;
    }
}
