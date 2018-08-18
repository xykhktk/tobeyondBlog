package com.tobeyond.blog.service.impl;

import com.tobeyond.blog.model.po.Maxim;
import com.tobeyond.blog.dao.mapper.MaximMapper;
import com.tobeyond.blog.service.IMaximsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaximServiceImpl implements IMaximsService {

    @Autowired
    MaximMapper maximMapper;

    @Override
    public List<Maxim> maximList() {
        return maximMapper.maximList();
    }

}
