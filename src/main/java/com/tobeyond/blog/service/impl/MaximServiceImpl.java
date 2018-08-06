package com.tobeyond.blog.service.impl;

import com.tobeyond.blog.dao.domain.Maxim;
import com.tobeyond.blog.dao.mapper.MaximMapper;
import com.tobeyond.blog.service.MaximsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaximServiceImpl implements MaximsService {

    @Autowired
    MaximMapper maximMapper;

    @Override
    public List<Maxim> maximList() {
        return maximMapper.maximList();
    }

}
