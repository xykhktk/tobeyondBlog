package com.tobeyond.blog.service;


import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.model.po.TagExample;
import com.tobeyond.blog.model.po.TagPo;

import java.util.List;

public interface ITagService {

//    List<TagBo> tagList();

    List<TagPo> selectByExample(TagExample example);

    PageInfo<TagPo> listWithPager(TagExample example, Integer page, Integer limit);

    Boolean add(TagPo po);

    Boolean update(TagPo po);

    Boolean del(TagPo po);

}
