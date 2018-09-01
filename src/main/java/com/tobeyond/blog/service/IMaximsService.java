package com.tobeyond.blog.service;


import com.tobeyond.blog.model.po.Maxim;
import com.tobeyond.blog.model.po.MaximExample;
import com.tobeyond.blog.model.po.MaximPo;

import java.util.List;

public interface IMaximsService {

    List<Maxim> maximList();

    List<MaximPo> selectByExample(MaximExample example);
}
