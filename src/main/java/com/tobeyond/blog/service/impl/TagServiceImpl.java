package com.tobeyond.blog.service.impl;

import com.tobeyond.blog.dao.mapper.TagMapper;
import com.tobeyond.blog.model.po.Tag;
import com.tobeyond.blog.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements ITagService {

    @Autowired
    TagMapper tagMapper;

    @Override
    public List<Tag> tagList() {
        return tagMapper.tagsList();
    }
}
