package com.tobeyond.blog.service;


import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.model.po.MaximExample;
import com.tobeyond.blog.model.po.MaximPo;

import java.util.List;

public interface IMaximsService {

    MaximPo selectByPrimaryKey(Integer id);

    List<MaximPo> selectByExample(MaximExample example);

    PageInfo<MaximPo>  listWithPager(Integer page,Integer limit);

    List<MaximPo> list(MaximExample example);

    Boolean add(MaximPo maximPo);

    Boolean update(MaximPo maximPo);

    Boolean del(MaximPo maximPo);

}
