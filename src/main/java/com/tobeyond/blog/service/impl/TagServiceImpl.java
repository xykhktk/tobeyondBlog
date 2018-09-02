package com.tobeyond.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.dao.mapper.TagMapper;
import com.tobeyond.blog.model.Bo.TagBo;
import com.tobeyond.blog.model.po.TagExample;
import com.tobeyond.blog.model.po.TagPo;
import com.tobeyond.blog.service.ITagService;
import com.tobeyond.blog.util.DateKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TagServiceImpl implements ITagService {

    @Autowired
    TagMapper tagMapper;

    @Override
    public List<TagPo> selectByExample(TagExample example) {
        return tagMapper.selectByExample(example);
    }

    @Override
    public PageInfo<TagPo> listWithPager(TagExample example, Integer page, Integer limit) {
        if(page == null) page = 1;
        if(limit == null) limit = 10;
        PageHelper.startPage(page, limit);
        List<TagPo> List = tagMapper.selectByExample(example);
        return new PageInfo<>(List);
    }

    @Override
    public Boolean add(TagPo po) {
        Date now = DateKit.getNow();
        po.setCreatedAt(now);
        po.setUpdatedAt(now);
//        tagMapper.insert(po);
        tagMapper.insertSelective(po);
        return true;
    }

    @Override
    public Boolean update(TagPo po) {
        po.setUpdatedAt(DateKit.getNow());

        TagExample example= new TagExample();
        example.createCriteria().andIdEqualTo(po.getId());
        tagMapper.updateByExampleSelective(po,example);
        return true;
    }

    @Override
    public Boolean del(TagPo po) {
        po.setDeletedAt(DateKit.getNow());
        TagExample example= new TagExample();
        example.createCriteria().andIdEqualTo(po.getId());
        tagMapper.updateByExampleSelective(po,example);
        return true;
    }


}
