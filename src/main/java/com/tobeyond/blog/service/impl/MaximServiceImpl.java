package com.tobeyond.blog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.dao.mapper.MaximMapper;
import com.tobeyond.blog.model.dto.ListWithPager;
import com.tobeyond.blog.model.po.MaximExample;
import com.tobeyond.blog.model.po.MaximPo;
import com.tobeyond.blog.service.IMaximsService;
import com.tobeyond.blog.util.DateKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public MaximPo selectByPrimaryKey(Integer id) {
        return maximMapper.selectByPrimaryKey(id);
    }

    @Override
    public ListWithPager listWithPager(Integer currentPage, Integer limit) {
        if(currentPage == null) currentPage = 1;
        if(limit == null) limit = 10;
        MaximExample maximExample = new MaximExample();
        maximExample.createCriteria().andDeletedAtIsNull();
        maximExample.setOrderByClause("id desc");
        Page page = PageHelper.startPage(currentPage, limit);
        List<MaximPo> List = maximMapper.selectByExample(maximExample);

        ListWithPager listWithPager = new ListWithPager();
        listWithPager.setTotal(page.getTotal());
        listWithPager.setList((ArrayList) List);
        listWithPager.setPageSize(page.getPageSize());
        listWithPager.setPageNum(page.getPageNum());
        return  listWithPager;
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
