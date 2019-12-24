package com.tobeyond.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.dao.mapper.MaximMapper;
import com.tobeyond.blog.model.po.MaximExample;
import com.tobeyond.blog.model.po.MaximPo;
import com.tobeyond.blog.service.IMaximsService;
import com.tobeyond.blog.util.DateKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MaximServiceImpl implements IMaximsService {

    @Autowired
    MaximMapper maximMapper;

    @Override
    public List<MaximPo> selectByExample(MaximExample example) {
        return maximMapper.selectByExample(example);
    }

    @Override
    public PageInfo<MaximPo> listWithPager(Integer page,Integer limit) {
        if(page == null) page = 1;
        if(limit == null) limit = 10;
        MaximExample maximExample = new MaximExample();
        maximExample.createCriteria().andDeletedAtIsNull();
        PageHelper.startPage(page, limit);
        List<MaximPo> List = maximMapper.selectByExample(maximExample);
        return new PageInfo<>(List);
    }

    @Override
    public List<MaximPo> list(MaximExample example) {
        List<MaximPo> List = maximMapper.selectByExample(example);
        return List;
    }

    @Override
    public Boolean add(MaximPo maximPo) {
        Date now = DateKit.getNow();
        maximPo.setCreatedAt(now);
        maximPo.setUpdatedAt(now);
        maximMapper.insert(maximPo);
        return true;
    }

    @Override
    public Boolean update(MaximPo maximPo) {
        maximPo.setUpdatedAt(DateKit.getNow());

        MaximExample maximExample= new MaximExample();
        maximExample.createCriteria().andIdEqualTo(maximPo.getId());
        maximMapper.updateByExampleSelective(maximPo,maximExample);
        return true;
    }

    @Override
    public Boolean del(MaximPo maximPo) {
        maximPo.setDeletedAt(DateKit.getNow());
        MaximExample maximExample= new MaximExample();
        maximExample.createCriteria().andIdEqualTo(maximPo.getId());
        maximMapper.updateByExampleSelective(maximPo,maximExample);
        return true;
    }

}
