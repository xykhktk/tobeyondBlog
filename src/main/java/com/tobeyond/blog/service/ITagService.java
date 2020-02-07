package com.tobeyond.blog.service;


import com.tobeyond.blog.model.dto.ListWithPager;
import com.tobeyond.blog.model.po.TagExample;
import com.tobeyond.blog.model.po.TagPo;

import java.util.List;

public interface ITagService {

    TagPo selectByPrimaryKey(Integer id);

    List<TagPo> selectByExample(TagExample example);

    ListWithPager listWithPager(Integer page, Integer limit);

    Boolean add(TagPo po);

    Boolean update(TagPo po);

    Boolean del(TagPo po);

}
